from flask import Flask, jsonify, request, make_response

app = Flask(__name__)

# シンプルなAPIエンドポイント
@app.route('/api/greet', methods=['GET'])
def greet():
    name = request.args.get('name', 'World')
    response = make_response(jsonify({'message': f'Hello, {name}!'}))
    response.headers['Content-Type'] = 'application/json; charset=utf-8'
    return response

if __name__ == '__main__':
    app.run(debug=True)
