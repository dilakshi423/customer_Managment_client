$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateProjectForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "CustomerManageAPI",
		type : type,
		data : $("#formCustomerManage").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onProjectSaveComplete(response.responseText, status);
		}
	});
});

function onProjectSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divProjectGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidProjectIDSave").val("");
	$("#formCustomerManage")[0].reset();
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidProjectIDSave").val(
					$(this).closest("tr").find('#hidProjectIDUpdate').val());
			$("#cusName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#cusAddress").val($(this).closest("tr").find('td:eq(1)').text());
			$("#cusNIC").val($(this).closest("tr").find('td:eq(2)').text());
			$("#cusEmail").val($(this).closest("tr").find('td:eq(3)').text());
			$("#cusPno").val($(this).closest("tr").find('td:eq(4)').text());
		});

// REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "CustomerManageAPI",
		type : "DELETE",
		data : "cusID=" + $(this).data("cusid"),
		dataType : "text",
		complete : function(response, status) {
			onProjectDeleteComplete(response.responseText, status);
		}
	});
});

function onProjectDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divProjectGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL=========================================================================
function validateProjectForm() {
	// NAME
	if ($("#cusName").val().trim() == "") {
		return "Insert Customer Name.";
	}

	
	if ($("#cusAddress").val().trim() == "") {
		return "Insert Address.";
	}
	
	if ($("#cusNIC").val().trim() == "") {
		return "Insert NIC.";
	}
	
	if ($("#cusEmail").val().trim() == "") {
		return "Insert Email.";
	}

	// Price------------------------
	 var tmpcusPno = $("#cusPno").val().trim();
	 if (!$.isNumeric(tmpcusPno)) 
	 {
		 return "Insert Phone Number.";
	 }


	return true;
}