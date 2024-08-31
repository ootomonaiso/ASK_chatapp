from flask import Flask, jsonify, request
import google.generativeai as genai 

app = Flask(__name__)

# Gemini APIの設定
genai.configure(api_key="YOUR_API_KEY")  # APIキーを設定

# モデルの指定
model = genai.GenerativeModel('models/gemini-1.0-pro-latest')

# カテゴリのリスト（事前設定）
CATEGORIES = [
    "学校基礎データ",
    "部活動の内容",
    "部活動の表彰",
    "学科の基本的な説明",
    "学科の活動"
]

@app.route('/api/find-relevant-category', methods=['POST'])
def find_relevant_category():
    # リクエストから質問を取得
    data = request.json
    question = data.get('question')
    
    if not question:
        return jsonify({'error': '質問が指定されていません'}), 400

    try:
        # 各カテゴリに対してGeminiモデルを使って関連性を確認
        results = {}
        for category in CATEGORIES:
            prompt = f"この質問が以下のカテゴリに最も関連していますか？ カテゴリ: {category} 質問: {question}"
            response = model.generate_content(prompt)
            relevance_score = response.text.strip().lower()  # モデルの応答から関連性スコアを取得
            # スコアを数値に変換
            score = 1 if relevance_score in ["yes", "yes, it is", "関連しています"] else 0
            results[category] = score

        # 最も関連性が高いカテゴリを選択
        most_relevant_category = max(results, key=results.get, default=None)

        return jsonify({'most_relevant_category': most_relevant_category}), 200

    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True)
