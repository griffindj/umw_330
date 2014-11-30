<#include "header.ftl">

<div class="container">

	<h2>Login / Register</h2>
	<#if error??>
		<p class="label label-danger">${error}</p>
	</#if>
	<div class="container">
		<div class="col-md-3">
		
			<form id="login-form" role="form" action="/login" method="post">
			  <div class="form-group">
			    <label for="email">Email</label>
			    <input type="email" class="form-control" name="email" id="email" placeholder="Enter email">
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
			  </div>
			  <button id="loginBtn" type="submit" class="btn btn-primary">Login</button>
			  <button id="registerBtn" type="submit" class="btn btn-danger">Register</button>
			  <button id="cancelBtn" type="button" class="btn">Cancel</button>
			</form>
			
		</div>
		
		<!--- List of all Public messages (disregarding subscriptions) --->
		<div class="col-md-9">
			<div class="panel panel-info">
			  <div class="panel-heading">
			    <h3 class="panel-title">Public TwitterAlt Feed</h3>
			  </div>
				<div class="panel-body">
					<ul class="list-group">
				  		<#list publicMessageFeed as message>
						    <li class="list-group-item">
						    	<div>
						    		${message.text}
						    		<div class="label label-default pull-right">
						    			${message.author} wrote at ${message.date?datetime}
						    		</div>
						    	</div>
						    	<div>
						    		<#list message.mentions as mention> 
						    			<span class="label label-primary">${mention}</span>
						    		</#list>
						    		<#list message.hashtags as hashtag> 
						    			<span class="label label-danger">${hashtag}</span>
						    		</#list>
						    	</div> 
						    </li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
		
	</div>

</div>

<script type="text/javascript">
//this is the javascript code that allows us to add different behaviors to our register and cancel buttons
//this changes the action (where the form gets submitted to) to /register
$( "#registerBtn" ).click(function() {
	$("#login-form").attr("action", "/register");
	$("#login-form").submit();
});
//this gets all inputs on our form, and resets the value to "reset" our form when cancel is clicked
$( "#cancelBtn" ).click(function() {
    $(':input','#login-form')
    .not(':button, :submit, :reset, :hidden')
    .val('')
    .removeAttr('checked')
    .removeAttr('selected');
});
</script>

<#include "footer.ftl">
