<#macro login path>
    <form action="${path}" method="post">
        <div class="d-flex justify-content-center">
        <div class="form-group row">
            <div class="col-sm-15">
            <input type="text" name="username" class="form-control" placeholder="username"/>
            </div>
        </div>
        </div>
        <div class="d-flex justify-content-center">
        <div class="form-group row">
            <div class="col-sm-15">
                <input type="password" name="password" class="form-control" placeholder="password"/>
            </div>
        </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="d-flex justify-content-center"><button class="btn btn-primary" type="submit">Войти</button></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-outline-light" type="submit">выйти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>