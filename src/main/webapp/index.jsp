<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="index.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">



<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
	integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Todo-App</title>
</head>
<style>
.btn-logout:hover {
	transform: scale(0.94) !important;
}
</style>

<body style="background-color: #f4f1de">
	<!-- CONTAINER PER LOGO E LOGOUT -->
	<div class="container mt-4">
		<div class="row align-items-center justify-content-between">
			<div class="col-auto">
				<img class="mb-1" src="logo.jpg" alt="" width="72" height="57">
				<h1 class="d-inline" style="font-family: 'Climate Crisis', cursive;">Todo-List</h1>
			</div>
			<div class="col-auto">
				<form method="post" action="logout">
					<button type="submit" class="btn btn-logout"
						style="background-color: #ef476f; color: #fff;">Logout</button>
				</form>
			</div>
		</div>
	</div>



	<!-- CONTAINER FORM SCELTE  -->
		<div class="container mt-1 ">
			<div class="row d-flex align-items-center justify-content-center">
				<div class="col-sm-12 col-md-6 col-lg-3 ">
					<form method="post" action="creazione-task">
						<div class="mb-3">
							<label for="nome" class="form-label">Descrizione</label>
							<input type="text" id="task" name="task" class="form-control">
						</div>
						<div class="mb-3">
							<label for="cognome" class="form-label">Data Creazione</label>
							<input type="date" id="dataCreazione" name="dataCreazione" class="form-control">
						</div>
						<div class="mb-3">
							<label for="dataNascita" class="form-label">Data Scadenza</label>
							<input type="date" id="dataScadenza" name="dataScadenza" class="form-control">
						</div>
						<input type="hidden" name="id" value="${utente.id}">
						<button type="submit" class="btn btn-success fw-bold">SALVA</button>
					</form>
				</div>
			</div>
		</div>

	<!-- CONTAINER TABELLA -->
	<div class="container container-tasks">
		<table class="table">
			<thead>
				<tr>
					<th scope="col"><i class="fa-solid fa-check"></i></th>
					<th scope="col">Task</th>
					<th scope="col">Aggiunta il</th>
					<th scope="col">Scadr� il</th>
					<th scope="col">Azioni</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="todo" items="${todoList}">
					<tr>
						<th scope="row"><input class="form-check-input"
							type="checkbox" value="" id="flexCheckDefault"></th>
						<td>${todo.descrizione}</td>
						<td><fmt:formatDate value="${todo.dataCreazione}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${todo.dataScadenza}"
								pattern="yyyy-MM-dd" /></td>
						<td class="action-icons"><i class="fa-regular fa-trash-can"></i>
							<i class="fa-sharp fa-solid fa-marker ms-2"></i></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>