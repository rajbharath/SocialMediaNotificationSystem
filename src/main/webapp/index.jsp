<!DOCTYPE html>
<html lang="en" ng-app='socialMediaApp'>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Social Media Notification System</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
    <!-- Angular -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular.min.js"></script>
    <script src="/js/welcome.js"></script>
  </head>
  <body ng-controller='welcome'>
  <div class="container-fluid">
              <div class="row">
                <div class="col-md-6">
                  <div class="panel panel-info">
                    <div class="panel-heading">Sign Up</div>
                    <div class="panel-body">
                      <form class="form-inline" action="http://localhost:8080/user" method="POST" role="form">
                          <div class="form-group">
                            <label class="sr-only" for="mail">Email address</label>
                            <input type="email" class="form-control" name="mail" id="mail" placeholder="Enter email">
                          </div>
                          <div class="form-group">
                            <label class="sr-only" for="name">Password</label>
                            <input type="text" class="form-control" name="name" id="name" placeholder="Enter username">
                          </div>
                          <button type="submit" class="btn btn-default">Create User</button>
                        </form>
                    </div>
                    </div>
                </div>
            </div>
             <div class="row">
                            <div class="col-md-6">
                              <div class="panel panel-info">
                                <div class="panel-heading">Sign In</div>
                                <div class="panel-body">
                                  <form class="form-inline" action="http://localhost:8080/user" method="GET" role="form">
                                      <div class="form-group">
                                        <label class="sr-only" for="mail">Email address</label>
                                        <input type="email" class="form-control" name="mail" id="mail" placeholder="Enter email">
                                      </div>
                                      <button type="submit" class="btn btn-default">Find User</button>
                                    </form>
                                </div>
                                </div>
                            </div>
                        </div>
                       <div class="table-responsive">
                         <table class="table">
                           <thead>
                           <tr>
                           <th>id</th>
                                       <th>name</th>
                                       <th>mail</th>
                                       <th>friends</th>
                                     </tr>
                                   </thead>
                                   <tbody>
                                   <tr ng-repeat="user in users">
                                   <td>{{user.id}}</td>
                                   <td>{{user.name}}</td>
                                   <td>{{user.mail}}</td>
                                   <td>{{user.friends}}</td>
                                   </tr>
                                   </tbody>
                         </table>
                       </div>
  </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
  </body>
</html>