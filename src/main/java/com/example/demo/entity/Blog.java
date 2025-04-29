package com.example.demo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;//@Entity アノテーションを使うためのインポート
import jakarta.persistence.GeneratedValue;
//@GeneratedValue アノテーションを使うためのインポート
//主キー（@Id で指定する項目）の値を自動生成するために使う
import jakarta.persistence.GenerationType;
//GenerationType は @GeneratedValue で使う戦略（strategy）を指定するための Enum（列挙型）
import jakarta.persistence.Id;//エンティティの主キー（ユニークなID）を設定するために使う
import jakarta.persistence.Table;//エンティティをデータベースのどのテーブルと結びつけるかを指定できる


@Entity
@Table(name = "blogs")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//フィールド
	private Integer id; // タスクID
	@Column(name = "category_id")
	private Integer categoryId;//カテゴリーID
	@Column(name = "user_id")
	private Integer userId;// ユーザーID
	
	private String title;// タイトル

	private String body;//内容
	
//	// 進捗ラベルを取得（表示用）
//    @Transient
//    public String getProgressLabel() {
//        return ProgressStatus.fromValue(this.progress).getLabel();
//    }
//    @Transient
//    public String getCategoryName() {
//        return CategoryMapper.getCategoryName(this.categoryId);
//    }
	
	//コンストラクタ
	public Blog() {
	}

	public Blog(Integer id,Integer categoryId,  String title, String body) {
		this.id=id;
		this.categoryId = categoryId;
		this.title = title;
		this.body = body;
	}
	public Blog(Integer categoryId,  String title, String body) {
		this.categoryId = categoryId;
		this.title = title;
		this.body = body;
	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}
}
