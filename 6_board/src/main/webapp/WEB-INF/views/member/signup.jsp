<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Signup</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="resources/fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="resources/vendor/jquery-ui/jquery-ui.min.css">
    <!-- Main css -->
    <link rel="stylesheet" href="resources/css/signup.css">
</head>
<body>
    <div class="main">
        <section class="signup">
            <div class="container">
                
                    <form action="signup" method="POST" id="signup-form" class="signup-form">
                    <h2>Signup</h2>
                        <div class="form-group">
                            <label for="userid">ID</label>
                            <input type="text" class="form-input" name="userid" id="userid" />
                        </div>
            			<div class="form-row">
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-input" name="userpwd" id="password"/>
                            </div>
                            <div class="form-group">
                                <label for="re_password">Repeat your password</label>
                                <input type="password" class="form-input" name="" id="re_password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="username">Name</label>
                            <input type="text" class="form-input" name="username" id="username" />
                        </div>
                        <div class="form-group">
                            <div class="form-radio">
                                <label for="gender">Gender</label>
                                <div class="form-flex">
                                    <input type="radio" name="gender" value="male" id="male" checked="checked" />
                                    <label for="male">Male</label>
    
                                    <input type="radio" name="gender" value="female" id="female" />
                                    <label for="female">Female</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone number</label>
                            <input type="number" class="form-input" name="phone" id="phone" />
                        </div>
                        <div class="form-text">
                               <div class="form-group">
                                   <label for="email">Email</label>
                                   <input type="email" class="form-input" name="email" id="email"/>
                               </div>
                               <div class="form-group">
                                   <label for="address">Address</label>
                 						<input type="text" class="form-input" name="address" id="address"/>
                               </div>
                        </div>
                        <div class="form-group">
                            <input type="reset"  name="submit" id="reset" class="form-submit"  value="취소"/>
                            <input type="submit" name="submit" id="submit" class="form-submit" value="회원가입"/>
                        </div>
                    </form>
                </div>
           
        </section>

    </div>

    <!-- JS -->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="resources/vendor/jquery-validation/dist/jquery.validate.min.js"></script>
    <script src="resources/vendor/jquery-validation/dist/additional-methods.min.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>