package com.yts.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDao {

	static final String LIST_ARTICLES = "select articleId, title, userId, name, left(cdate,16) cdate from article order by articleId desc limit ?,?";

	static final String COUNT_ARTICLES = "select count(articleId) from article";

	static final String GET_ARTICLE = "select articleId, title, content, userId, name, left(cdate,16) cdate, udate from article where articleId=?";

	static final String ADD_ARTICLE = "insert article(title,content,userId,name) values(?,?,?,?)";
	
	static final String UPDATE_ARTICLE = "update article set title=?, content=? where articleId=?";
	
	static final String DELETE_ARTICLE = "delete from article where articleId=?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Article> articleRowMapper = new BeanPropertyRowMapper<>(
			Article.class);

	public List<Article> listArticles(int offset, int count) {
		return jdbcTemplate.query(LIST_ARTICLES, articleRowMapper, offset,
				count);
	}

	public int getArticlesCount() {
		return jdbcTemplate.queryForObject(COUNT_ARTICLES, Integer.class);
	}

	public Article getArticle(String articleId) {
		return jdbcTemplate.queryForObject(GET_ARTICLE, articleRowMapper,
				articleId);
	}

	public int addArticle(Article article) {
		return jdbcTemplate.update(ADD_ARTICLE, article.getTitle(),
				article.getContent(), article.getUserId(), article.getName());
		}
	
	public int updateArticle(Article article) {
		return jdbcTemplate.update(UPDATE_ARTICLE, article.getTitle(), article.getContent(), article.getArticleId());
		
	}
	public void deleteArticle(Article article) {
		jdbcTemplate.update(DELETE_ARTICLE, article.getArticleId());
	}
}