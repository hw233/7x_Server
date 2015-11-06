package com.manu.dynasty.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qx.persistent.HibernateUtil;
import com.qx.util.TableIDCreator;
import com.qx.yuanbao.YuanBaoInfo;

public class AdminServlet extends HttpServlet {
	public static Logger log = LoggerFactory.getLogger(AdminServlet.class);
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			log.info("action null");
			return;
		}
		if (action.equals("logout")) {
			logout(request, response);
		} else if (action.equals("list")) {
			list(request, response);
		} else if (action.equals("regist")) {
			regist(request, response);
		} else if (action.equals("update")) {
			update(request, response);
		} else if (action.equals("delete")) {
			delete(request, response);
		}
	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Admin> list = HibernateUtil.list(Admin.class, "where 1=1");
		request.setAttribute("list", list);
		request.getRequestDispatcher("gm/adminlist.jsp").forward(request,
				response);
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		Admin admin = HibernateUtil.find(Admin.class, Long.valueOf(id));
		admin.setName(name);
		admin.setPwd(pwd);
		admin.setUpdatetime(new Date());
		admin.setCreateuser(((Admin) request.getSession().getAttribute("admin"))
				.getId());
		HibernateUtil.save(admin);
		request.getRequestDispatcher("admin?action=list").forward(request,
				response);
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		HibernateUtil.delete(HibernateUtil.find(Admin.class, Long.valueOf(id)));
		request.getRequestDispatcher("admin?action=list").forward(request,
				response);
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		Admin tmp = HibernateUtil
				.find(Admin.class, "where name='" + name + "'");
		if (null != name && null != pwd) {
			if (null != tmp) {// 用户名已存在，直接修改用户
				tmp.setName(name);
				tmp.setPwd(pwd);
				tmp.setUpdatetime(new Date());
				tmp.setCreateuser(((Admin) request.getSession().getAttribute(
						"admin")).getId());
				HibernateUtil.save(tmp);
			} else {// 用户不存在
				Admin admin = new Admin();
				admin.setId(TableIDCreator.getTableID(Admin.class, 1L));
				admin.setName(name);
				admin.setPwd(pwd);
				admin.setUpdatetime(new Date());
				admin.setCreateuser(((Admin) request.getSession().getAttribute(
						"admin")).getId());
				HibernateUtil.insert(admin);
			}
		}
		request.getRequestDispatcher("admin?action=list").forward(request,
				response);
	}

	/**
	 * 注销
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("admin");// 移除session中的用户信息
		response.sendRedirect("login.jsp");// 回到登录页
	}
}
