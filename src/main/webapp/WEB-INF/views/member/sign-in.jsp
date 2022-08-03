<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="../include/static-head.jsp" %>

    <style>
        .wrap {
            margin: 200px auto;
        }

    </style>
</head>
<body>
    <%@ include file="../include/header.jsp" %>

    <div class="container wrap">
        <div class="row">
            <div class="offset-md-2 col-md-4">
                <div class="card" style="width:200%;">
                    <div class="card-header text-white" style="background: #343A40;">
                        <h2><span style="color: gray;">MVC</span> 로그인</h2>               
                    </div>
                    <div class="card-body">
                        
                        <form action="/member/sign-in" name="sign-in" method="post" id="signInForm"
                        style="margin-bottom: 0;">
                        <table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>아이디를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" name="account" id="signInId"
                                    class="form-control tooltipstered" maxlength="10"
                                    required="required" aria-required="true"
                                    style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최대 10자"></td>
                            </tr>
                            <tr>
                                <td style="text-align: left">
                                    <p><strong>비밀번호를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="pwCheck"></span></p>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="password" size="17" maxlength="20" id="signInPw"
                                    name="password" class="form-control tooltipstered" 
                                    maxlength="20" required="required" aria-required="true"
                                    style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
                                    placeholder="최소 8자"></td>
                            </tr>
                            
                            <!-- 자동 로그인 체크박스 -->
                            <tr>
                                <td>
                                    <label for="auto-login">
                                        <span>
                                        <i class="fas fa-sign-in-alt"></i>
                                        <!-- <i class="fa fa-sign-in" aria-hidden="true"></i>  -->
                                        자동 로그인
                                        <input type="checkbox" id="auto-login" name="autoLogin">
                                        <!-- input name 값이 LoginDTO 필드 값과 같아야 한다 -->
                                        </span>
                                    </label>
                                </td>
                            </tr>
                            
                            <tr>
                                <td style="padding-top: 10px; text-align: center">
                                    <p><strong>로그인하셔서 더 많은 서비스를 이용해보세요!</strong></p>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 100%; text-align: center; colspan: 2;"><input
                                    type="submit" value="로그인" class="btn form-control tooltipstered" id="signIn-btn"
                                    style="background-color: #343A40; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
                                </td>
                            </tr>
                            <tr>
                                <td
                                    style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
    
                                    <a class="btn form-control tooltipstered" href="/member/sign-up"
                                    style="cursor: pointer; margin-top: 0; height: 40px; color: white; background-color: gray; border: 0px solid #388E3C; opacity: 0.8">
                                        회원가입</a>
                                </td>
                            </tr>
                            <tr>
                                <td   style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
    
                                    <a id="custom-login-btn" href="https://kauth.kakao.com/oauth/authorize?client_id=9727a2bba3b021a605228cd4978e3491&redirect_uri=http://localhost/auth/kakao&response_type=code">
                                        <img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>

        const msg = '${msg}';

        if (msg === 'reg-success') {
            alert('축하합니다! 회원가입에 성공했습니다.')
        }

        const loginMsg = '${loginMsg}';
        if (loginMsg === 'NO_ACC') {

            alert('존재하지 않는 회원입니다.')

        } else if (loginMsg === 'NO_PW') {

            alert('비밀번호가 틀렸습니다.')
        }

        const warning = '${warningMsg}';
        if (warning === 'forbidden') {
            
            alert('로그인 후 사용할 수 있습니다.');
        }


    </script>


    <%@ include file="../include/footer.jsp" %>
</body>