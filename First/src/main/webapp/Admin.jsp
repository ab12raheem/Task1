<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet" href="pageStyle.css">

</head>
<body>
	<header>
		<span class="logo"> <span>A</span>dmin
		</span>
	</header>
	<ul class="nav">
		<li class="nav-li"><input type="button" value="Task"
			class="nav-button"></li>
		<li><input type="button" value="Employee" class="nav-button"></li>
		<li><input type="button" value="logout" class="nav-button"></li>
		<li><a href="<%=request.getContextPath()%>/task.do"
			class="nav-button">tasks</a></li>

	</ul>

	<section class="task view">
		<table class="taskTable">
			<tr>
				<th>id</th>
				<th>description</th>
				<th>attachment</th>
				<th>status</th>
				<th>actions</th>


			</tr>

			<c:forEach var="task" items="${listTask}">
				<tr>
					<td><c:out value="${task.id}" /></td>
					<td><c:out value="${task.description}" /></td>
					<td><c:out value="${task.attachment}" /></td>
					<td><c:out value="${task.status}" /></td>
					<td><a href="deleteTask.do?id=<c:out value='${task.id}' />">Delete</a>"        "<a href="TaskShowEditForm.do?id=<c:out value='${task.id}' />">edit</a>    </td>
					



				</tr>

			</c:forEach>
			<tr>
				<form action="getTasksByStatus.do">
					<div>
						<input type="radio" id="inProgress" name="status"
							value="inProgress" checked> <label for="inProgress">inProgress</label>
					</div>

					<div>
						<input type="radio" id="done" name="status" value="done">
						<label for="done">done</label>
					</div>
					<input type="submit" class="button" value="filter">


				</form>
			</tr>

			<tr>
				<form action="getTasksByEmployee.do">
					<div>
						<span>Employee</span> <input type="text" name="userName"
							class="box">
					</div>

					<input type="submit" class="button" value="filter">


				</form>
			</tr>

		</table>


	</section>

	<section class="task forms">
		<c:if test="${task != null}">
			<form action="updateTask.do" method="post">
		</c:if>
		<c:if test="${task == null}">
			<form action="addTask.do" method="post">
		</c:if>
		<span>description</span>
		<c:if test="${task != null}">
			<input type="text" name="description" class="box"
				value="${task.description}">
			<br>
		</c:if>
		<c:if test="${task == null}">
			<input type="text" name="description" class="box" value="">
			<br>

		</c:if>
		<c:if test="${task != null}">
			<input type="hidden" name="id" value="${task.id}">
			<span>attachment</span>
			<input type="text" name="attachment" class="box"
				value="${task.attachment}">
			<br>
		</c:if>

		<c:if test="${task == null}">
			<span>attachment</span>
			<input type="text" name="attachment" class="box" value="">
			<br>
		</c:if>
		<div>

			<input type="radio" id="inProgress" name="status" value="inProgress"
				checked> <label for="inProgress">inProgress</label>
		</div>

		<div>
			<input type="radio" id="done" name="status" value="done"> <label
				for="done">done</label>
		</div>
		<input type="submit" class="button" value="add">
		</form>


		<form action="addTaskToEmployee.do" method="post">
			<span>Task id</span> <input type="text" name="id" class="box">
			<br> <span>Employee </span> <input type="text" name="userName"
				class="box"> <br> <input type="submit" class="button"
				value="assign">
		</form>
	</section>


	<section class="employee view"></section>
	<section class="employee form"></section>
</body>
</html>