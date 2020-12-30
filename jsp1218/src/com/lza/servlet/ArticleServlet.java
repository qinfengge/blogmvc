package com.lza.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lza.dao.imp.ArticleDaoImp;
import com.lza.dao.imp.CommentDaoImp;
import com.lza.dao.imp.TagDaoImp;
import com.lza.util.FileUtil;
import com.lza.vo.Article;
import com.lza.vo.Tag;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet{
	private static final String BASE_URL = "http://127.0.0.1:8080";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String opr = request.getParameter("opr");
		ArticleDaoImp adi = new ArticleDaoImp();
		if("ser".equals(opr)) {
			String artser = request.getParameter("artser");
			try {
				List<Article> alist= adi.selByArticleName(artser);
				if(alist!=null) {
					request.setAttribute("alist", alist);
					request.getRequestDispatcher("search.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//提交前，查询标签，返回
		if("toaddart".equals(opr)) {
			TagDaoImp tdi = new TagDaoImp();
			try {
				List<Tag> ltags = tdi.selAllTags();
				request.setAttribute("ltags", ltags);
				request.getRequestDispatcher("/article/addart.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("addart".equals(opr)) {
			try {
				//1.判断requets是否为多部分文本表单请求
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				// 上传文件的存储路径（服务器文件系统上的绝对文件路径）
				String uploadFilePath = getServletContext().getRealPath("upload");
				//D：\\tomcat\webapp\news_chapter03\\upload
				String uploadFilePath2 = "E:\\tts9\\workspace\\jsp1218\\WebContent\\upload";
				File uploadFile = new File(uploadFilePath);
				if (!uploadFile.isDirectory()) {// upload目录不存在，创建目录
					uploadFile.mkdir();
				}
				Article art = new Article();
				if(isMultipart) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					//				factory.setRepository(new File("D:\\TEMP\\"));//缓存目录
					factory.setSizeThreshold(1024*1024*5);
					ServletFileUpload upload = new ServletFileUpload(factory);// 创建解析文件表单的工具类
					upload.setSizeMax(1024 * 1024 * 5);//设置请求内容大小
					// 解析form表单中所有文件
					List<FileItem> items;
					items = upload.parseRequest(request);
					boolean isValidImg = true; // 是否为不允许的图片类型
					File saveFile = null; // 上传并保存的文件
					for (FileItem item : items) { // 依次处理每个文件
						if (item.isFormField()) { // 普通表单字段
							String fieldName = item.getFieldName(); // 表单字段的name属性值
							if (fieldName.equals("artname")) { // 文章名
								art.setArticleName(item.getString("UTF-8"));//控件的value值
							} else if (fieldName.equals("atag")) { // 文章标签
								art.setTagId(Integer.parseInt(item.getString("UTF-8")));
							} else if (fieldName.equals("author")) {// 作者
								art.setAuthor(item.getString("UTF-8"));
							} else if (fieldName.equals("acontent")) { // 文章内容
								art.setarticleContent(item.getString("UTF-8"));
							}
						} else { // 文件表单字段
							String fileName = item.getName();// 获取文件的文件名
							if (fileName.length() > 0) {
								List<String> fileType = Arrays.asList("gif", "jpg", "jpeg");

								int index = fileName.lastIndexOf(".");
								String houzhui = (index == -1) ? "" : fileName.substring(index + 1).toLowerCase();

								// 判断文件类型是否在允许范围内
								if (fileType.contains(houzhui)) {
									//使用系统时间毫秒数作为图片名，防止图片重名被覆盖
									String saveFileName = System.currentTimeMillis() + "."+houzhui;
									saveFile = new File(uploadFilePath, saveFileName);//目录 D:\\abc\a.jpg

									//									saveFile.createNewFile();
									//									saveFile.mkdir();

									item.write(saveFile);// 复制图片到指定路径
									//									item.write(new File(uploadFilePath2, saveFileName));//复制图片到指定路径
									FileUtil.copy(saveFile, new File(uploadFilePath2, saveFileName));

									//http://127.0.0.1:8080/news_chapter03/upload/123.jpg
									String picPath = BASE_URL + request.getContextPath() +"/upload/" + saveFileName;
									art.setImgSrc(picPath);
								} else {
									isValidImg = false;
								}
							}
						}
					}
					if (!isValidImg) {
						request.setAttribute("type", "1");
					}else {
						// 添加新闻
						art.setArticeDate(new Date());
						art.setArticleId(0);
						int count = new ArticleDaoImp().addArticle(art);
						if (count > 0) {
							request.setAttribute("type", 2);
						} else {
							request.setAttribute("type", 3);
						}
					}
					//					request.setAttribute("news", news);
					request.getRequestDispatcher("/article/addart.jsp").forward(request, response);
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//查询全部文章，返回给list页面
		if("listart".equals(opr)) {
			try {
				List<Article> listart = adi.selAllarticle();
				if(listart!=null) {
					request.setAttribute("listart", listart);
					request.getRequestDispatcher("/article/listart.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//根据ID删除
		if("delart".equals(opr)) {
			try {
			String artid = request.getParameter("artlid");
			Article a = new Article();
			a.setArticleId(Integer.parseInt(artid));
				int count = adi.delArticle(a);
				if(count>0) {
					response.sendRedirect("/tag/listart.jsp");
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//修改文章前查询文章和标签，返回给页面
		if("toupdart".equals(opr)) {
			try {
			String artid = request.getParameter("artlid");
			int aid = Integer.parseInt(artid);
				List<Article> alist = adi.selByArtId(aid);
				TagDaoImp tdi = new TagDaoImp();
				List<Tag> tlist = tdi.selAllTags();
				if(alist!=null&&tlist!=null) {
					request.setAttribute("listart", alist);
					request.setAttribute("listtag", tlist);
					request.getRequestDispatcher("/article/updart.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//修改文章
		if("updart".equals(opr)){
			try {
			String aidStr = request.getParameter("aid");
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// 上传文件的存储路径（服务器文件系统上的绝对文件路径）
			String uploadFilePath = getServletContext().getRealPath("upload");
			String uploadFilePath2 = "E:\\tts9\\workspace\\jsp1218\\WebContent\\upload";

			File uploadFile = new File(uploadFilePath);
			if (!uploadFile.isDirectory()) {// upload目录不存在，创建目录
				uploadFile.mkdir();
			}
			int aid = Integer.parseInt(aidStr);
			Article art = adi.selByArtIdaa(aid);
			if(isMultipart) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//				factory.setRepository(new File("D:\\TEMP\\"));//缓存目录
				factory.setSizeThreshold(1024*1024*5);
				ServletFileUpload upload = new ServletFileUpload(factory);// 创建解析文件表单的工具类
				upload.setSizeMax(1024 * 1024 * 5);//设置请求内容大小
				// 解析form表单中所有文件
				List<FileItem> items;
				items = upload.parseRequest(request);
				boolean isValidImg = true; // 是否为不允许的图片类型
				File saveFile = null; // 上传并保存的文件
				for (FileItem item : items) { // 依次处理每个文件
					if (item.isFormField()) { // 普通表单字段
						String fieldName = item.getFieldName(); // 表单字段的name属性值
						if (fieldName.equals("artname")) { // 文章名
							art.setArticleName(item.getString("UTF-8"));//控件的value值
						} else if (fieldName.equals("atag")) { // 文章标签
							art.setTagId(Integer.parseInt(item.getString("UTF-8")));
						} else if (fieldName.equals("author")) {// 作者
							art.setAuthor(item.getString("UTF-8"));
						} else if (fieldName.equals("acontent")) { // 文章内容
							art.setarticleContent(item.getString("UTF-8"));
						}
					} else { // 文件表单字段
						String fileName = item.getName();// 获取文件的文件名
						if (fileName.length() > 0) {
							List<String> fileType = Arrays.asList("gif", "jpg", "jpeg");

							int index = fileName.lastIndexOf(".");
							String houzhui = (index == -1) ? "" : fileName.substring(index + 1).toLowerCase();

							// 判断文件类型是否在允许范围内
							if (fileType.contains(houzhui)) {
								//使用系统时间毫秒数作为图片名，防止图片重名被覆盖
								String saveFileName = System.currentTimeMillis() + "."+houzhui;
								saveFile = new File(uploadFilePath, saveFileName);//目录 D:\\abc\a.jpg

								//									saveFile.createNewFile();
								//									saveFile.mkdir();

								item.write(saveFile);// 复制图片到指定路径
								//									item.write(new File(uploadFilePath2, saveFileName));//复制图片到指定路径
								FileUtil.copy(saveFile, new File(uploadFilePath2, saveFileName));

								//http://127.0.0.1:8080/news_chapter03/upload/123.jpg
								String picPath = BASE_URL + request.getContextPath() +"/upload/" + saveFileName;
								art.setImgSrc(picPath);
							} else {
								isValidImg = false;
							}
						}
					}
				}
				if (!isValidImg) {
					request.setAttribute("type", "1");
				}else {
					// 修改新闻
					int count = new ArticleDaoImp().updArticle(art);
					if (count > 0) {
						request.setAttribute("type", 2);
					} else {
						request.setAttribute("type", 3);
					}
				}
				//					request.setAttribute("news", news);
				request.getRequestDispatcher("/article/updart.jsp").forward(request, response);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//查询全部文章，返回给index页面
				if("init".equals(opr)) {
					try {
						List<Article> listart = adi.selAllarticle();
						if(listart!=null) {
							request.setAttribute("listart", listart);
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}else {
							request.getRequestDispatcher("error.jsp").forward(request, response);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		//文章页面
				if("showart".equals(opr)) {
					try {
						String aidStr = request.getParameter("aid");
						int aid = Integer.parseInt(aidStr);
						List<Article> listart = adi.selByArtId(aid);
						if(listart!=null) {
							request.setAttribute("listart", listart);
							request.getRequestDispatcher("/article/showart.jsp").forward(request, response);
						}else {
							request.getRequestDispatcher("error.jsp").forward(request, response);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
	}
}
