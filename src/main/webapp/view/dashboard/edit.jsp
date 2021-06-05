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
                    <div class="col-sm-6">
                        <a href="create.html" class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Add
                                New Product</span></a>
                    </div>
                </div>
            </div>
            <div class="">
                <input type="hidden" name="productId" value="${product.id}" />

                <form action="admin/product/edit/?id=${ product.id }" method="post">
                    <div class="modal-header">
                        <button class="back" style="vertical-align: middle"
                                onclick="window.location.href='product-menu'; return false;">
                            <i class="material-icons" data-toggle="tooltip" title="Back to list">&#xe5c4;</i>
                        </button>
                        <h4 style="display: inline-block" class="modal-title">Update Product Detail</h4>
                        <div style="display: inline-block; float: right">
                            <button type="submit" class="save">
                                <i class="material-icons" style="font-weight: bold; padding-right: 10px" data-toggle="tooltip" title="Save">&#xe5ca;</i>
                            </button>
                            <button class="cancel"
                                    onclick="window.location.href='product-menu/show/?id=${ product.id }'; return false;">
                                <i class="material-icons" style="font-weight: bold" data-toggle="tooltip" title="Cancel">&#xe5cd;</i>
                            </button>
                        </div>
                    </div>

                    <div class="modal-body">


                        <input type="hidden" name="version" value="${ product.version }">

                        <div class="form-group">
                            <label>name:</label>
                            <input class="form-control" type="text" name="name" value="${ product.name }" required>
                        </div>

                        <div class="form-group">
                            <label>productCategoryId:</label>
                            <input class="form-control" type="text" name="productCategoryId"
                                   value="${ product.productCategoryId }" required>
                        </div>

                        <div class="form-group">
                            <label>restaurantId:</label>
                            <input class="form-control" type="text" name="restaurantId" value="${ product.restaurantId }"
                                   required>
                        </div>

                        <div class="form-group">
                            <label>ingredients:</label>
                            <input class="form-control" type="text" name="ingredients" value="${ product.ingredients }"
                                   required>
                        </div>

                        <div class="form-group">
                            <label>price:</label>
                            <input class="form-control" type="text" name="price" value="${ product.price }" required>
                        </div>

                        <div class="required-group">
                            <label>image:</label>
                            <input class="form-control" type="text" name="image" value="${ product.image }" required>
                        </div>

                        <div class="form-group">
                            <label>country:</label>
                            <input class="form-control" type="text" name="country" value="${ product.country }" required>
                        </div>

                        <div class="form-group">
                            <label>tag:</label>
                            <input class="form-control" type="text" name="tag" value="${ product.tag }" required>
                        </div>

                        <div class="form-group">
                            <label>description:</label>
                            <textarea class="form-control" id="description" name="description" required>${ product.description }</textarea>
                        </div>

                        <div class="form-group">
                            <label>featured:</label>
                            <input class="form-control" type="text" name="featured" value="${ product.featured }" required>
                        </div>

                        <div class="form-group">
                            <label>createdAt:</label>
                            <p>${ product.createdAt }</p>
                        </div>

                        <div class="form-group">
                            <label>createdBy:</label>
                            <p>${ product.createdBy }</p>
                        </div>

                        <div class="form-group">
                            <label>updatedAt:</label>
                            <p>${ product.updatedAt }</p>
                        </div>

                        <div class="form-group">
                            <label>updatedBy:</label>
                            <p>${ product.updatedBy }</p>
                        </div>

                        <div class="form-group">
                            <label>version:</label>
                            <p>${ product.version }</p>
                        </div>

                        <div class="form-group">
                            <label>deleted:</label>
                            <p>${ product.deleted }</p>
                        </div>

                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>


                    </div>
                </form>

            </div>
        </div>
    </div>
</body>

<script src="assets/script.js"></script>

</html>