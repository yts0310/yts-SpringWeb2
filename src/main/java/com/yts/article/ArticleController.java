package com.yts.article;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.yts.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ArticleController {

	@Autowired
	ArticleDao articleDao;



	/**
	 * 글 목록
	 */
	@GetMapping("/article/list")
	public void articleList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		// 페이지당 행의 수와 페이지의 시작점
		final int COUNT = 100;
		int offset = (page - 1) * COUNT;

		List<Article> articleList = articleDao.listArticles(offset, COUNT);
		int totalCount = articleDao.getArticlesCount();
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articleList", articleList);
	}

	/**
	 * 글 보기
	 */
	@GetMapping("/article/view")
	public void articleView(@RequestParam("articleId") String articleId,
			Model model) {
		Article article = articleDao.getArticle(articleId);
		model.addAttribute("article", article);
	}

	/**
	 * 글 등록 화면
	 */
	@GetMapping("/article/addForm")
	public String articleAddForm(HttpSession session) {
		return "article/addForm";
	}

	/**
	 * 글 등록
	 */
	@PostMapping("/article/add")
	public String articleAdd(Article article, @SessionAttribute("MEMBER") Member member) {
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		articleDao.addArticle(article);
		return "redirect:/app/article/list";
	}
	
	@GetMapping("/article/update")
	public String articleUpdate(@RequestParam("articleId") String articleId, Model model, HttpSession session,
		@SessionAttribute("MEMBER") Member member) throws Exception {
		Article article = articleDao.getArticle(articleId);
		Object memberObj = session.getAttribute("MEMBER");
		if (memberObj == null)
			return "login/loginForm";
		if(!member.getMemberId().equals(article.getUserId()))
			return "article/updateFail";
		model.addAttribute("article",article);
		return "article/update";
	}
	
	@PostMapping("/article/update2")
	public String articleUpdate2(Article article,
			@SessionAttribute("MEMBER") Member member) {
			articleDao.updateArticle(article);
			return "redirect:/app/article/list";
	}
	
	
	@GetMapping("/article/delete")
	public String deleteArticle(@RequestParam("articleId") String articleId, Model model, HttpSession session,
			@SessionAttribute("MEMBER") Member member) throws Exception {
			Article article = articleDao.getArticle(articleId);
			Object memberObj = session.getAttribute("MEMBER");
			if (memberObj == null)
				return "login/loginForm";
			if(!member.getMemberId().equals(article.getUserId()))
				return "article/deleteFail";
			articleDao.deleteArticle(article);
			return "article/delete";
   }
}