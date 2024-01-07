from flask import Flask, render_template, request, redirect, url_for, jsonify
from flask_sqlalchemy import SQLAlchemy
import random


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///friends_blog.db'
db = SQLAlchemy(app)

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(50), unique=True, nullable=False)
    race = db.Column(db.String(50))
    email = db.Column(db.String(120), unique=True, nullable=False)
    phone_number = db.Column(db.String(15))

    friends = db.relationship('Friendship',
                             foreign_keys='Friendship.user_id',
                             backref='user', lazy=True)

class Friendship(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    friend_id = db.Column(db.Integer, nullable=False)

db.create_all()

def suggest_friends(user_id):
    user = User.query.get(user_id)
    if user:
        friends = [friend.friend_id for friend in user.friends]
        suggestions = User.query.filter(
            (User.id != user_id) &
            ((User.race == user.race) | (User.email == user.email) | (User.phone_number == user.phone_number)) &
            (~User.id.in_(friends))
        ).all()
        return suggestions
    return []

@app.route('/add_user', methods=['POST'])
def add_user():
    data = request.get_json()
    new_user = User(**data)
    db.session.add(new_user)
    db.session.commit()

    return jsonify({'message': 'User added successfully'})

@app.route('/add_friend', methods=['POST'])
def add_friend():
    data = request.get_json()
    user_id = data['user_id']
    friend_id = data['friend_id']

    friendship = Friendship(user_id=user_id, friend_id=friend_id)
    db.session.add(friendship)
    db.session.commit()

    return jsonify({'message': 'Friend added successfully'})

@app.route('/remove_friend', methods=['POST'])
def remove_friend():
    data = request.get_json()
    user_id = data['user_id']
    friend_id = data['friend_id']

    friendship = Friendship.query.filter_by(user_id=user_id, friend_id=friend_id).first()
    if friendship:
        db.session.delete(friendship)
        db.session.commit()
        return jsonify({'message': 'Friend removed successfully'})
    else:
        return jsonify({'message': 'Friend not found'})

@app.route('/get_friends', methods=['GET'])
def get_friends():
    user_id = request.args.get('user_id')

    user = User.query.get(user_id)
    if user:
        friends = [friend.friend_id for friend in user.friends]
        suggested_friends = [user.id for user in suggest_friends(user_id)]
        return jsonify({'friends': friends, 'suggested_friends': suggested_friends})
    else:
        return jsonify({'friends': [], 'suggested_friends': []})

# Feed + Blog Feed Routes
feed_data = [
    {'id': 1, 'title': 'Post 1', 'content': 'This is the content of post 1.'},
    {'id': 2, 'title': 'Post 2', 'content': 'This is the content of post 2.'},
    # Add more sample data as needed
]

blog_data = [
    {'id': 1, 'title': 'Blog Post 1', 'content': 'This is the content of blog post 1.'},
    {'id': 2, 'title': 'Blog Post 2', 'content': 'This is the content of blog post 2.'},
    # Add more sample data as needed
]

@app.route('/')
def main_page():
    return render_template('main_page.html')

@app.route('/feed')
def feed():
    return render_template('feed.html', feed_data=feed_data)

@app.route('/blog_feed')
def blog_feed():
    # Shuffle blog data for randomness
    random.shuffle(blog_data)
    return render_template('blog_feed.html', blog_data=blog_data)

@app.route('/create_blog', methods=['POST'])
def create_blog():
    data = request.form
    new_blog = {
        'id': len(blog_data) + 1,
        'title': data['title'],
        'content': data['content']
    }
    blog_data.append(new_blog)
    return redirect(url_for('main_page'))

if __name__ == '__main__':
    app.run(debug=True)
