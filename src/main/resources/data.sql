-- categories テーブルにデータを挿入
 	INSERT INTO categories (id, name) VALUES
 	(1, '仕事'),
 	(2, '遊び'),
 	(3, '勉強');
 	 
 	-- users テーブルにデータを挿入
 	INSERT INTO users (id, email, name, password) VALUES
 	(1, 'tanaka@aaa.com', '田中太郎', 'test123'),
 	(2, 'suzuki@aaa.com', '鈴木一郎', 'test456');
 	 
 	-- blogs テーブルにデータを挿入
 	INSERT INTO blogs (id, category_id, user_id, title, body) VALUES
 	(1, 1, 1, '見積もり', '見積もり金額を明日までに提出');
 	 
 	 -- シーケンスの位置を補正
-- 最後に挿入したIDが 3（= id:1,2,3）なので、次は 4 にしたい
SELECT setval('categories_id_seq', 3, true);

-- 最後に挿入したIDが 2 なので、次は 3 にしたい
SELECT setval('users_id_seq', 2, true);

-- blogs では id:1 しか使ってないので、次は 2 にしたい
SELECT setval('blogs_id_seq', 1, true);