
// Initialize variables to keep track of money and coinsvar moneytotal = 0;
var dollar = 0;
var quarter = 0;
var dime = 0;
var nickel = 0;
var penny = 0;
var moneytotal = 0;

$(document).ready(function () {

  // Load products on page load

  loadProducts();

  $("#dollar").click(function () {
    addCash(1.00);
  });

  // Add event listeners to the buttons to add cash to the machine

  $("#quarter").click(function () {
    addCash(0.25);
  });

  $("#dime").click(function () {
    addCash(0.10);
  });

  $("#nickle").click(function () {
    addCash(0.05);
  });

  $("#penny").click(function () {
    addCash(0.01);
  });

    // Function to add cash to the machine and update the display

  function addCash(money) {
    moneytotal += money;
    $("#money-insert").val(moneytotal.toFixed(2));
  }
  // Add event listener to the Make Purchase button

  $('#makePurchase').click(function () {
        // Get the amount of cash inserted and the selected item number
    var getChange = $("#money-insert").val();
    var selecteditem = $('#productNumber').val();
        // Reset the money total to 0 and call the makePurchase function
    moneytotal = 0;
    makePurchase(getChange, selecteditem);
  });

  // Function to make a purchase

  function makePurchase(getChange, selecteditem) {

        // Send an AJAX request to the vending machine API to make a purchase

    $.ajax({
      type: "POST",
      url: 'http://vending.us-east-1.elasticbeanstalk.com/money/' + getChange + '/item/' + selecteditem,
      success: function (data) {
        
                // Calculate the amount of change in dollars, quarters, dimes, nickels, and pennies
               // This calculates the total value of coins inserted into a vending machine by a user. 

        dollar = data.quarters * 0.25 + data.dimes * 0.10 + data.nickels * 0.05 + data.pennies * 0.01;
        quarter = data.quarters;
        dime = data.dimes;
        nickel = data.nickels;
        penny = data.pennies;
        var change = '';

                // If the change is in dollar form, add the dollar amount to the change string

        if (dollar % 1 == 0) {
          var dollarStr = dollar.toFixed(2);
          change += dollarStr + ' dollar ';
        }else{
          // Otherwise, add the number of coins to the change string
      
        if (quarter > 0) {
          change += quarter + ' quarter ';
        }
        if (dime > 0) {
          change += dime + ' dime ';
        }
        if (nickel > 0) {
          change += nickel + ' nickel ';
        }
        if (penny > 0) {
          change += penny + ' penny ';
        }
      }
              // Update the display with the amount of change returned

        $('#moneyReturned').val(change.trim());
      },

            // Handle errors and display error message

      error: function errorMessages(errorThrown) {
        var error = errorThrown.responseJSON.message + "";
        $('#message').val(error);
      }
    });
  }

  loadProducts();

  function clearProductTable() {
    $("#product").empty();
  }


  function loadProducts() {
    clearProductTable();
  
    var contentRows = $('#contentRows');

      // Send a GET request to the server to get the products

    $.ajax({
      type: 'GET',
      url: 'http://vending.us-east-1.elasticbeanstalk.com/items',
      success: function (data) {
        $.each(data, function (index, product) {
          var name = product.name;
          var price = product.price;
          var id = product.id;

          // For each product returned by the server, create a card and add it to the page

          var card = '<div class="card">';
          card += '<div class="card-body">';
          card += '<h5 class="card-title">' + name + '</h5>';
          card += '<h6 class="card-subtitle mb-2 text-muted">' + price.toFixed(2) + '</h6>';
          card += '<button class="btn btn-primary pick-item-button" data-item-id="' + id + '">Pick</button>';
          card += '</div></div>';
          contentRows.append(card);
        });
  
        $('.pick-item-button').click(function() {
          var id = $(this).data('item-id');
          $('#productNumber').val(id);

        });
      },
      error: function(xhr, status, error) {
        console.log("Error: " + error);
      }
    });
  }

  
        function clearPriceTable() {
          $("#money-insert").empty();
        }

        function clearChangeTable() {
          $("#moneyReturned").empty();
        }
      
      

        $('#changeReturned').click(function () {
          clearPriceTable();
          clearChangeTable();
          $('#moneyReturned').val("");
          $('#money-insert').val("");
          $('#productNumber').val("");
          $('#message').val("");


        
          var change = "";
        
          if (dollar >= 1) {
            var dollars = Math.floor(dollar);
            dollar -= dollars;
            change += dollars + " dollar ";
          }
        
          var remainingChange = Math.round(dollar * 100) + quarter * 25 + dime * 10 + nickel * 5 + penny;
        
          if (remainingChange > 0) {
            var quartersNeeded = Math.floor(remainingChange / 25);
            remainingChange -= quartersNeeded * 25;
            if (quartersNeeded > 0) {
              change += quartersNeeded + " quarter ";
            }
        
            var dimesNeeded = Math.floor(remainingChange / 10);
            remainingChange -= dimesNeeded * 10;
            if (dimesNeeded > 0) {
              change += dimesNeeded + " dime ";
            }
        
            var nickelsNeeded = Math.floor(remainingChange / 5);
            remainingChange -= nickelsNeeded * 5;
            if (nickelsNeeded > 0) {
              change += nickelsNeeded + " nickel ";
            }
        
            var penniesNeeded = remainingChange;
            if (penniesNeeded > 0) {
              change += penniesNeeded + " penny";
            }
          }
        
          $('#moneyReturned').val(change.trim());
        });

     
      

    });
