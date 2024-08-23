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
                    <tr>
                        <td>${articleItem}</td>
                        <g:form controller="article" resource="${articleItem}" method="POST">
                            <td><g:actionSubmit value="View" action="show"/></td>
                        </g:form>
                    </tr>
                </g:each>
            </table>
        </div>
    </body>
</html>