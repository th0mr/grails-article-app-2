<!DOCTYPE html>
<html>
    <head>
        <title>Articles</title>
    </head>
    <body>
        <div>
            <h1>Articles</h1>
            <table>
                <g:each in="${articleList}" var="articleItem">
                    <tr><td>${articleItem}</td></tr>
                </g:each>
            </table>
        </div>
    </body>
</html>