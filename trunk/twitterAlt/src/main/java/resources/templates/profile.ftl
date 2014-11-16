<#include "header.ftl">

<div class="container">

	<div class="page-header">
	  <h1>Welcome to TwitterAlt <small>an alternative to twitter</small></h1>
	  <div class="pull-right">
	  	<form role="form" action="search" method="post">
	  		<input type="text" name="query" placeholder="Search Messages..."></input>
			<button type="submit" class="btn btn-primary">Search</button>
	  	</form>
	  </div>
	</div>

	<div class="container">
		<div class="col-md-5">
			<div class="panel panel-danger">
			  <div class="panel-heading">
			    <h3 class="panel-title">Your TwitterAlt Profile</h3>
			  </div>
			  <div class="panel-body">
			  	<p>${user.username}</p>
			  	<a href="/resetPassword">reset your password</a>
			  </div>
			</div>
			<div class="panel panel-default">
			  	<div class="panel-body">
					<form role="form" action="/postMessage" method="post">
				  		<div class="form-group">
				    		<label for="text">Write a Message</label>
				    		<textarea class="form-control" rows="3" id="text" name="text" placeholder="Write a message"></textarea>
				  		</div>
						<div class="checkbox">
							<label>
								<input type="checkbox" name="isPublic" value="true" checked > show message to public
							</label>
						</div>
				  		<div class="pull-right">
					  		<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-pencil"></span> Post Message</button>
					  		<button type="button" class="btn" >Cancel</button>
					  	</div>
					</form>
			  	</div>
			  	<div class="panel-heading">
			    	<h3 class="panel-title">
		    			<span class="label label-primary">@mention</span>
		    			<span class="label label-danger">#hashtag</span>
			    		<div class=" pull-right">
					    	<span class="label label-default">
					    		you have written <span class="badge">${user.messages?size}</span> messages
					    	</span>
					    </div>
			    	</h3>
			  	</div>
			  	<div class="panel-body">
			  		<ul class="list-group">
				  		<#list user.messages as message>
						    <li class="list-group-item">
						    	<div>
						    		${message.text}
						    		<div class="badge pull-right"><span class="glyphicon glyphicon-remove"></span></div>
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
		<div class="col-md-7">
			<div class="panel panel-info">
			  <div class="panel-heading">
			    <h3 class="panel-title">Your TwitterAlt feed</h3>
			  </div>
				<div class="panel-body">
					<ul class="list-group">
				  		<#list messageFeed as message>
						    <li class="list-group-item">
						    	<div>
						    		${message.text}
						    		<div class="label label-default pull-right">${message.date?datetime}</div>
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

<#include "footer.ftl">
