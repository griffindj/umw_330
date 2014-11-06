<#include "header.ftl">

<div class="container">

	<h2>Login / Register</h2>

	<div class="container">
		<div class="col-md-6">
		
			<form role="form" action="/login" method="post">
			  <div class="form-group">
			    <label for="email">Email</label>
			    <input type="email" class="form-control" id="email" placeholder="Enter email">
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" id="password" placeholder="Password">
			  </div>
			  <button type="submit" class="btn btn-primary">Login</button>
			  <button type="button" class="btn btn-danger" >Register</button>
			  <button type="button" class="btn" >Cancel</button>
			</form>
			
		</div>
	</div>

</div>


<#include "footer.ftl">
