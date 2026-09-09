<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="org.springframework.security.authentication.encoding.PasswordEncoder"%>
<%@page import="java.util.List"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.bcs.zsg.core.cache.service.CacheService"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.apache.commons.lang.math.RandomUtils"%>
<%@page import="com.bcs.zsg.core.helper.BaseContext"%>
<%@page import="java.util.UUID"%>
<%@page import="com.bcs.zsg.component.common.vo.SysParamVO"%>
<%@page import="com.bcs.zsg.core.helper.BeanFactory"%>
<%@page import="com.bcs.zsg.component.common.dao.SysParamDAO"%>
<%
	try {
		String action = request.getParameter("action");
		int minMemberId = 10001;
		int memberId = minMemberId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(
				BeanFactory.getBean("dataSource", DataSource.class));
		int maxRandom = 10;
		int minimumCount = 5;
		
		PasswordEncoder passwordEncoder = BeanFactory.getBean("passwordEncoder", PasswordEncoder.class);
		
		String insertUserSql = "INSERT INTO SEC_USER (UUID, LOGIN_ID, LOGIN_PSWD, USER_NAME, USER_ACCT_ID, CREATED_BY, UPD_BY, DT_UPD)"
								+ " VALUES (?, ?, ?, ?, ?, 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP)";
		
		String insertUserRoleSql = "INSERT INTO SEC_USER_ROLE (U_USER, U_ROLE) VALUES (?, ?)";

		String insertMemberSql = "INSERT INTO MEMBER_USER (MEMBER_ID, UPLINE_ID, U_MEMBER_ROLE, CREATED_BY, UPD_BY, DT_UPD)" 
								+ " VALUES (?, ?, ?, 'SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP)";
		
		if (StringUtils.equals(action, "create-unlimited")) {
			List<String> roleList = jdbcTemplate.queryForList("SELECT UUID FROM VW_MEMBER_ROLE ORDER BY ORDER_SEQ_NO", String.class);
			
			// first level only for top level members
			
			for (int i = 0; i < 10; i++) {
				String userUUID = "U_" + memberId;
				String loginId = "user" + memberId;
				String loginPassword = passwordEncoder.encodePassword("password", loginId);
				String name = "Name " + memberId;
				
				// insert into MEMBER_USER
				jdbcTemplate.update(insertMemberSql, memberId, null, roleList.get(0));
				
				// insert into SEC_USER
				jdbcTemplate.update(insertUserSql, userUUID, loginId, loginPassword, name, memberId);
				
				// insert into SEC_USER_ROLE
				jdbcTemplate.update(insertUserRoleSql, userUUID, roleList.get(0));
				jdbcTemplate.update(insertUserRoleSql, userUUID, "ROLE_USER");
				jdbcTemplate.update(insertUserRoleSql, userUUID, "ROLE_MEMBER");
				
				memberId++;
			}
			
		} else if (StringUtils.equals(action, "create-pyramid")) {

			// get member roles (levels)
			List<String> roleList = jdbcTemplate.queryForList("SELECT UUID FROM VW_MEMBER_ROLE ORDER BY ORDER_SEQ_NO", String.class);
			
			int roleIdx = 0;
			Map<Integer, List<Integer>> roleListMap = new HashMap<Integer, List<Integer>>();
			
			for (String role : roleList) {
				int count = RandomUtils.nextInt(maxRandom);
				maxRandom *= 1.5;
				count = count < minimumCount ? minimumCount : count;

				List<Integer> userRoleList = new ArrayList<Integer>();
				roleListMap.put(roleIdx, userRoleList);
				
				for (int i = 0; i < count; i++) {
					String userUUID = "U_" + memberId;
					String loginId = "user" + memberId;
					String loginPassword = passwordEncoder.encodePassword("password", loginId);
					String name = "Name " + memberId;
					
					userRoleList.add(memberId);
					
					Integer uplineId = null;
					
					if (roleIdx > 0) {
						List<Integer> tempRoleList = roleListMap.get(roleIdx - 1);
						int tempMaxRandom = tempRoleList.size() - 1;
						tempMaxRandom = (tempMaxRandom < 0) ? 0 : tempMaxRandom;
						
						int tempIdx = RandomUtils.nextInt(tempMaxRandom);
						uplineId = tempRoleList.get(tempIdx);
					}
					
					// insert into MEMBER_USER
					jdbcTemplate.update(insertMemberSql, memberId, uplineId, role);
					
					// insert into SEC_USER
					jdbcTemplate.update(insertUserSql, userUUID, loginId, loginPassword, name, memberId);
					
					// insert into SEC_USER_ROLE
					jdbcTemplate.update(insertUserRoleSql, userUUID, role);
					jdbcTemplate.update(insertUserRoleSql, userUUID, "ROLE_USER");
					jdbcTemplate.update(insertUserRoleSql, userUUID, "ROLE_MEMBER");
					
					memberId++;
				}
				
				roleIdx++;
			}

		} else if (StringUtils.equals(action, "clear")) {

			String deleteMemberConfigSql = "delete from member_cfg";
			String deleteMemberSql = "delete from member_user";
			String deleteUserRoleSql = "delete from sec_user_role where u_user <> 'SUPERUSER'";
			String deleteUserSql = "delete from sec_user where uuid <> 'SUPERUSER'";
			
			jdbcTemplate.update(deleteMemberConfigSql);
			jdbcTemplate.update(deleteUserRoleSql);
			jdbcTemplate.update(deleteUserSql);
			jdbcTemplate.update(deleteMemberSql);
			
		} else
			out.println("Pass in ?action=create or ?action=clear");

	} catch (Throwable t) {
		t.printStackTrace();
		out.println("<span style='color: red;'>" + t + "</span>");

	} finally {
	}
%>