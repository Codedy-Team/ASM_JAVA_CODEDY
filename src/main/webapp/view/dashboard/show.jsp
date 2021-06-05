<!DOCTYPE html>
<html lang="en">

<head>
    <base href="">

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
                <div class="modal-header">
                    <button class="back" style="vertical-align: middle"
                        onclick="window.location.href='index.html'; return false;">
                        <i class="material-icons" data-toggle="tooltip" title="Back to list">&#xe5c4;</i>
                    </button>
                    <h4 style="display: inline-block" class="modal-title">
                        Product Detail
                    </h4>
                    <div style="display: inline-block; float: right;">
                        <a href="edit.html" class="edit" style="margin-right: 10px">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <form action="#delete" method="post" style="display: inline-block">
                            <input type="hidden" name="id" value="">
                            <button class="delete" type="submit"
                                onclick="return confirm('Are you sure delete this item?')">
                                <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                            </button>
                        </form>
                    </div>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label>id:</label>
                        <p>56</p>
                    </div>

                    <div class="form-group">
                        <label>name:</label>
                        <p>Cappuccino</p>
                    </div>

                    <div class="form-group">
                        <label>productCategoryId:</label>
                        <p>10</p>
                    </div>

                    <div class="form-group">
                        <label>restaurantId:</label>
                        <p>1</p>
                    </div>

                    <div class="form-group">
                        <label>ingredients:</label>
                        <p>Coffee, milk, cream</p>
                    </div>

                    <div class="form-group">
                        <label>price:</label>
                        <p>$12.68</p>
                    </div>

                    <div class="form-group">
                        <label>image:</label>
                        <br>
                        <img style="height: 120px;" src="assets/data-images/products/capuchino.jpg" alt="product.image">
                        <p>capuchino.jpg</p>
                    </div>

                    <div class="form-group">
                        <label>country:</label>
                        <p>Italy</p>
                    </div>

                    <div class="form-group">
                        <label>tag:</label>
                        <p>Dinner</p>
                    </div>

                    <div class="form-group">
                        <label>description:</label>
                        <p>As cappuccino is defined today, in addition to a single shot of espresso, the most important
                            factors in preparing a cappuccino are the texture and temperature of the milk. When a
                            barista steams the milk for a cappuccino, microfoam is created by introducing very tiny
                            bubbles of air into the milk, giving the milk a velvety texture. The traditional cappuccino
                            consists of a single espresso, on which the barista pours the hot foamed milk, resulting in
                            a 2 cm (3⁄4 in) thick milk foam on top. Variations could be made adding another shot of
                            espresso resulting in a double cappuccino. Attaining the correct ratio of foam requires
                            close attention while steaming the milk, thus making the cappuccino one of the most
                            difficult espresso-based beverages to make properly. A skilled barista may obtain artistic
                            shapes (latte art while pouring the milk on the top of the espresso coffee).</p>
                    </div>

                    <div class="form-group">
                        <label>featured:</label>
                        <p>true</p>
                    </div>

                    <div class="form-group">
                        <label>createdAt:</label>
                        <p>2021-06-03</p>
                    </div>

                    <div class="form-group">
                        <label>createdBy:</label>
                        <p>Hieu_iceTea</p>
                    </div>

                    <div class="form-group">
                        <label>updatedAt:</label>
                        <p></p>
                    </div>

                    <div class="form-group">
                        <label>updatedBy:</label>
                        <p></p>
                    </div>

                    <div class="form-group">
                        <label>version:</label>
                        <p>1</p>
                    </div>

                    <div class="form-group">
                        <label>deleted:</label>
                        <p>false</p>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>

<script src="assets/script.js"></script>

</html>