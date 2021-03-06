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
		
			<!--- Form to write and list to display messages the logged in user writes --->
			<div class="panel panel-default">
			  	<div class="panel-body">
					<form role="form" action="/postMessage" method="post">
				  		<div class="form-group">
				    		<label for="text">Write a Message</label> <span id="text_limit_display" class="pull-right"></span>
				    		<span id="text_limit_display"/><textarea class="form-control" rows="3" id="text" name="text" placeholder="Write a message"></textarea>
				  		</div>
						<div class="checkbox">
							<label>
								<input type="checkbox" name="isPublic" value="true" checked > show message to public
							</label>
						</div>
				  		<div class="pull-right">
					  		<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-pencil"></span> Post Message</button>
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
						    		<div href="deleteMessage?postedDate=${message.date?datetime}" 
						    				class="deleteMessageButton btn btn-xs badge pull-right">
						    			<span class="glyphicon glyphicon-remove"></span>
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
			
			<!--- Cancel Account/Password Reset --->
			<div class="panel panel-danger">
			  <div class="panel-heading">
			    <h3 class="panel-title">${user.username}'s TwitterAlt Profile</h3>
			  </div>
			  <div class="panel-body">
                <form  class="form-inline" role="form" action="/subscribe" method="post">
                    <select name="subscribee" class="form-control">
                        <option value="">Subscribe to a User</option>
                        <#list availableUsers as subscription>
                            <option value="${subscription}">${subscription}</option>
                        </#list>
                    </select>
                    <button type="submit" class="btn btn-primary">Subscribe</button>
                </form>
			  </div>
              <div class="panel-body">
                <ul class="list-group">
                    <#list user.subscriptions as sub>
                        <#if sub??>
                            <li class="list-group-item">
                            	<div>
                                    ${sub}
                                    <div href="deleteSubscription?username=${sub}" 
                                    		class="unsubscribeButton btn btn-xs badge pull-right">
                                    	<span class="glyphicon glyphicon-remove"></span>
                                    </div>
                                </div>
                            </li>
                        </#if>
                    </#list>
                </ul>
			  </div>
			  <div class="panel-body">
			  	<a class="btn btn-default" href="/resetPassword">reset your password</a>
                <a id="deleteAccount" class="btn btn-default" href="/deleteUser">delete your account</a>
			  </div>
			</div>
		</div>
		
		<!--- List of all messages from a user's subscriptions --->
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
$('#text').keyup(function () {
    var left = 140 - $(this).val().length;
    if (left < 0) {
        left = 0;
    }
    $('#text_limit_display').text('Characters left: ' + left);
});

$(".unsubscribeButton").confirm({
    text: "Are you sure you want to unsubscribe?",
    confirmButton: "Unsubscribe",
    title: "Confirmation Required to Proceed",
    cancelButton: "Cancel",
    post: true
});

$("#deleteAccount").confirm({
    text: "Are you sure you want to delete your account?",
    confirmButton: "Delete Account",
    title: "Confirmation Required to Proceed",
    cancelButton: "Cancel",
    post: true
});

$(".deleteMessageButton").confirm({
    text: "Are you sure you want to delete this message?",
    confirmButton: "Delete",
    title: "Confirmation Required to Proceed",
    cancelButton: "Cancel",
    post: true
});

</script>

<#include "footer.ftl">
