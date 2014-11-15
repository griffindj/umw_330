<#include "header.ftl">

<div class="container">

	<div class="page-header">
	  <h1>Welcome to TwitterAlt <small>an alternative twitter</small></h1>
	</div>

	<div class="container">
		<div class="col-md-3">
			<div class="panel panel-primary">
			  <div class="panel-heading">
			    <h3 class="panel-title">Profile</h3>
			  </div>
			  <div class="panel-body">
			  	${user.username}
			  </div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="row">
				<form class="well" role="form" action="/postMessage" method="post">
			  		<div class="form-group">
			    		<label for="message">Write a Message</label>
			    		<textarea class="form-control" rows="3" id="message" placeholder="Write a message"></textarea>
			  		</div>
			  		<div class="form-group pull-right">
				  		<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-pencil"></span> Post Message</button>
				  		<button type="button" class="btn" >Cancel</button>
				  	</div>
				</form>
			</div>
			<div class="row">
				<div class="panel panel-default">
				  	<div class="panel-heading">
				    	<h3 class="panel-title">Messages that you have written</h3>
				  	</div>
				  	<div class="panel-body">
				  		<ul class="list-group">
					  		<#list user.messages as message>
							    <li class="list-group-item">${message.text}</li>
							</#list>
						</ul>
				  	</div>
				</div>
			</div>
		</div>
	</div>

</div>


<#include "footer.ftl">
