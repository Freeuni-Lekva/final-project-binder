$(document).ready(function() {

    let id = null;
    let userInfo = null;
    let bannedUsers = null;

    function getUserInfo() {
        $.ajax({
            async: false,
            type: "POST",
            url: "GetUserDetailsServlet",
            data: {"userID": id},
            success: function (msg) {
                userInfo = msg;
            },
            error: function (msg) {
                alert("Unexpected Error!");
            }
        });
    }

    function getBannedUsers(){
        $.ajax({
            async: false,
            type: "POST",
            url: "GetBannedUsersServlet",
            success: function (msg) {
                bannedUsers = msg;
            },
            error: function (msg) {
                alert("Unexpected Error!");
            }
        });
    }

    function banUser(user_id) {
        $.ajax({
            async: false,
            type: "POST",
            url: "BanUserServlet",
            data: {"userID": user_id},
            success: function (msg) {
                let response = JSON.parse(msg);
                let status = response.status;
                if (status == 1) {
                    console.log("User got Banned Successfully!");
                }
                else if (status == 2){
                    console.log("Something went wrong");
                }
            },
            error: function (msg) {
                alert("Unexpected Error!");
            }
        });
    }

    function unbanUser(user_id){
        $.ajax({
            async: false,
            type: "POST",
            url: "UnbanUserServlet",
            data: {"userID": user_id},
            success: function (msg) {
                let response = JSON.parse(msg);
                let status = response.status;
                if (status == 1) {
                    console.log("User got unbanned Successfully!");
                }
                else if (status == 2){
                    console.log("Something went wrong");
                }
            },
            error: function (msg) {
                alert("Unexpected Error!");
            }
        });
    }



});