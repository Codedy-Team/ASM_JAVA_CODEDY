<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="<c:url value="/"/>">
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product Menu</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="assets/style.css">

    <style>
        .form-group p {
            font-size: 135%;
        }
    </style>

<body>
    <div class="container">
        <div style="margin-top: 15px">
            <a style="background-color: greenyellow; padding: 5px; border-radius: 10%" href="index.html">Home</a> |

            <span>Welcome, <b
                    style="background-color: greenyellow; padding: 5px; border-radius: 10%">Username</b></span>

            <form style="display: inline" action="logout" method="post">
                <input type="submit" value="Logout">
            </form>
        </div>

        <div class="table-wrapper" style="margin-top: 15px">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Product Menus</b></h2>
                    </div>
                </div>
            </div>
            <div class="">

                <form action="admin/product/create" method="post">
                    <div class="modal-header">
                        <button class="back" style="vertical-align: middle"
                            onclick="window.location.href='index.html'; return false;">
                            <i class="material-icons" data-toggle="tooltip" title="Back to list">&#xe5c4;</i>
                        </button>
                        <h4 style="display: inline-block" class="modal-title">Create Product</h4>
                        <div style="display: inline-block; float: right">
                            <button type="submit" class="save">
                                <i class="material-icons" style="font-weight: bold; padding-right: 10px"
                                    data-toggle="tooltip" title="Save">&#xe5ca;</i>
                            </button>
                            <button class="cancel" onclick="window.location.href='index.html'; return false;">
                                <i class="material-icons" style="font-weight: bold" data-toggle="tooltip"
                                    title="Cancel">&#xe5cd;</i>
                            </button>
                        </div>
                    </div>

                    <div class="modal-body">
                        <div class="form-group">
                            <label>name:</label>
                            <input class="form-control" type="text" name="name" required>
                        </div>

                        <div class="form-group">
                            <label>productCategoryId:</label>
                            <input class="form-control" type="text" name="productCategoryId" value="" required>
                        </div>

                        <div class="form-group">
                            <label>restaurantId:</label>
                            <input class="form-control" type="text" name="restaurantId" required>
                        </div>

                        <div class="form-group">
                            <label>ingredients:</label>
                            <input class="form-control" type="text" name="ingredients" required>
                        </div>

                        <div class="form-group">
                            <label>price:</label>
                            <input class="form-control" type="text" name="price" required>
                        </div>

                        <div class="form-group">
                            <form action="<%=request.getContextPath()%>" method="post" enctype="multipart/form-data">
                            <label>image:</label>
                            <input class="form-control" type="file" name="image" required>
                            </form>
                        </div>

                        <div class="required-group">
                            <label>country:</label>
                            <input class="form-control" type="text" name="country" required>
                        </div>

                        <div class="form-group">
                            <label>tag:</label>
                            <input class="form-control" type="text" name="tag" required>
                        </div>

                        <div class="form-group">
                            <label>description:</label>
                            <textarea class="form-control" id="description" rows="5" name="description"
                                required></textarea>
                        </div>

                        <div class="form-group">
                            <label>featured:</label>
                            <input class="form-control" type="text" name="featured" required>
                        </div>

                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>

                        <div class="modal-footer">
                            <a href="/view/dashboard/index.jsp">Back to list</a>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</body>

<script src="assets/script.js"></script>

</html>