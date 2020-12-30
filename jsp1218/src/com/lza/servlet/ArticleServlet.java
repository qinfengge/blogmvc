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
		//�ύǰ����ѯ��ǩ������
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
				//1.�ж�requets�Ƿ�Ϊ�ಿ���ı�������
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				// �ϴ��ļ��Ĵ洢·�����������ļ�ϵͳ�ϵľ����ļ�·����
				String uploadFilePath = getServletContext().getRealPath("upload");
				//D��\\tomcat\webapp\news_chapter03\\upload
				String uploadFilePath2 = "E:\\tts9\\workspace\\jsp1218\\WebContent\\upload";
				File uploadFile = new File(uploadFilePath);
				if (!uploadFile.isDirectory()) {// uploadĿ¼�����ڣ�����Ŀ¼
					uploadFile.mkdir();
				}
				Article art = new Article();
				if(isMultipart) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					//				factory.setRepository(new File("D:\\TEMP\\"));//����Ŀ¼
					factory.setSizeThreshold(1024*1024*5);
					ServletFileUpload upload = new ServletFileUpload(factory);// ���������ļ����Ĺ�����
					upload.setSizeMax(1024 * 1024 * 5);//�����������ݴ�С
					// ����form���������ļ�
					List<FileItem> items;
					items = upload.parseRequest(request);
					boolean isValidImg = true; // �Ƿ�Ϊ�������ͼƬ����
					File saveFile = null; // �ϴ���������ļ�
					for (FileItem item : items) { // ���δ���ÿ���ļ�
						if (item.isFormField()) { // ��ͨ���ֶ�
							String fieldName = item.getFieldName(); // ���ֶε�name����ֵ
							if (fieldName.equals("artname")) { // ������
								art.setArticleName(item.getString("UTF-8"));//�ؼ���valueֵ
							} else if (fieldName.equals("atag")) { // ���±�ǩ
								art.setTagId(Integer.parseInt(item.getString("UTF-8")));
							} else if (fieldName.equals("author")) {// ����
								art.setAuthor(item.getString("UTF-8"));
							} else if (fieldName.equals("acontent")) { // ��������
								art.setarticleContent(item.getString("UTF-8"));
							}
						} else { // �ļ����ֶ�
							String fileName = item.getName();// ��ȡ�ļ����ļ���
							if (fileName.length() > 0) {
								List<String> fileType = Arrays.asList("gif", "jpg", "jpeg");

								int index = fileName.lastIndexOf(".");
								String houzhui = (index == -1) ? "" : fileName.substring(index + 1).toLowerCase();

								// �ж��ļ������Ƿ�������Χ��
								if (fileType.contains(houzhui)) {
									//ʹ��ϵͳʱ���������ΪͼƬ������ֹͼƬ����������
									String saveFileName = System.currentTimeMillis() + "."+houzhui;
									saveFile = new File(uploadFilePath, saveFileName);//Ŀ¼ D:\\abc\a.jpg

									//									saveFile.createNewFile();
									//									saveFile.mkdir();

									item.write(saveFile);// ����ͼƬ��ָ��·��
									//									item.write(new File(uploadFilePath2, saveFileName));//����ͼƬ��ָ��·��
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
						// �������
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
		//��ѯȫ�����£����ظ�listҳ��
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
		//����IDɾ��
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
		//�޸�����ǰ��ѯ���ºͱ�ǩ�����ظ�ҳ��
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
		//�޸�����
		if("updart".equals(opr)){
			try {
			String aidStr = request.getParameter("aid");
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// �ϴ��ļ��Ĵ洢·�����������ļ�ϵͳ�ϵľ����ļ�·����
			String uploadFilePath = getServletContext().getRealPath("upload");
			String uploadFilePath2 = "E:\\tts9\\workspace\\jsp1218\\WebContent\\upload";

			File uploadFile = new File(uploadFilePath);
			if (!uploadFile.isDirectory()) {// uploadĿ¼�����ڣ�����Ŀ¼
				uploadFile.mkdir();
			}
			int aid = Integer.parseInt(aidStr);
			Article art = adi.selByArtIdaa(aid);
			if(isMultipart) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//				factory.setRepository(new File("D:\\TEMP\\"));//����Ŀ¼
				factory.setSizeThreshold(1024*1024*5);
				ServletFileUpload upload = new ServletFileUpload(factory);// ���������ļ����Ĺ�����
				upload.setSizeMax(1024 * 1024 * 5);//�����������ݴ�С
				// ����form���������ļ�
				List<FileItem> items;
				items = upload.parseRequest(request);
				boolean isValidImg = true; // �Ƿ�Ϊ�������ͼƬ����
				File saveFile = null; // �ϴ���������ļ�
				for (FileItem item : items) { // ���δ���ÿ���ļ�
					if (item.isFormField()) { // ��ͨ���ֶ�
						String fieldName = item.getFieldName(); // ���ֶε�name����ֵ
						if (fieldName.equals("artname")) { // ������
							art.setArticleName(item.getString("UTF-8"));//�ؼ���valueֵ
						} else if (fieldName.equals("atag")) { // ���±�ǩ
							art.setTagId(Integer.parseInt(item.getString("UTF-8")));
						} else if (fieldName.equals("author")) {// ����
							art.setAuthor(item.getString("UTF-8"));
						} else if (fieldName.equals("acontent")) { // ��������
							art.setarticleContent(item.getString("UTF-8"));
						}
					} else { // �ļ����ֶ�
						String fileName = item.getName();// ��ȡ�ļ����ļ���
						if (fileName.length() > 0) {
							List<String> fileType = Arrays.asList("gif", "jpg", "jpeg");

							int index = fileName.lastIndexOf(".");
							String houzhui = (index == -1) ? "" : fileName.substring(index + 1).toLowerCase();

							// �ж��ļ������Ƿ�������Χ��
							if (fileType.contains(houzhui)) {
								//ʹ��ϵͳʱ���������ΪͼƬ������ֹͼƬ����������
								String saveFileName = System.currentTimeMillis() + "."+houzhui;
								saveFile = new File(uploadFilePath, saveFileName);//Ŀ¼ D:\\abc\a.jpg

								//									saveFile.createNewFile();
								//									saveFile.mkdir();

								item.write(saveFile);// ����ͼƬ��ָ��·��
								//									item.write(new File(uploadFilePath2, saveFileName));//����ͼƬ��ָ��·��
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
					// �޸�����
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
		//��ѯȫ�����£����ظ�indexҳ��
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
		//����ҳ��
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
