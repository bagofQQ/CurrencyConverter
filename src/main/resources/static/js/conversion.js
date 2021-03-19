$(function(){

    //Show conversion form
    $('#show-conversion-form').click(function(){
        $('#conversion-form').css('display', 'flex');
        $('#rurIn').click(function(){
            $('#rurIn').attr('disabled', true);
            $('#rurIn').css("border-color","blue");
            var originalAmountNameL = 'rur';
            $('#oan').val(originalAmountNameL);
            $('#usdIn').attr('disabled', true);
            $('#eurIn').attr('disabled', true);
            $('#gbpIn').attr('disabled', true);
        });
        $('#usdIn').click(function(){
            $('#usdIn').attr('disabled', true);
            $('#usdIn').css("border-color","blue");
            var originalAmountNameL = 'usd';
            $('#oan').val(originalAmountNameL);
            $('#rurIn').attr('disabled', true);
            $('#eurIn').attr('disabled', true);
            $('#gbpIn').attr('disabled', true);
        });
        $('#eurIn').click(function(){
            $('#eurIn').attr('disabled', true);
            $('#eurIn').css("border-color","blue");
            var originalAmountNameL = 'eur';
            $('#oan').val(originalAmountNameL);
            $('#rurIn').attr('disabled', true);
            $('#usdIn').attr('disabled', true);
            $('#gbpIn').attr('disabled', true);
        });
        $('#gbpIn').click(function(){
            $('#gbpIn').attr('disabled', true);
            $('#gbpIn').css("border-color","blue");
            var originalAmountNameL = 'gbp';
            $('#oan').val(originalAmountNameL);
            $('#rurIn').attr('disabled', true);
            $('#usdIn').attr('disabled', true);
            $('#eurIn').attr('disabled', true);
        });

        $('#rurOut').click(function(){
            $('#rurOut').attr('disabled', true);
            $('#rurOut').css("border-color","blue");
            var originalAmountNameL = 'rur';
            $('#tan').val(originalAmountNameL);
            $('#usdOut').attr('disabled', true);
            $('#eurOut').attr('disabled', true);
            $('#gbpOut').attr('disabled', true);
        });
        $('#usdOut').click(function(){
            $('#usdOut').attr('disabled', true);
            $('#usdOut').css("border-color","blue");
            var originalAmountNameL = 'usd';
            $('#tan').val(originalAmountNameL);
            $('#rurOut').attr('disabled', true);
            $('#eurOut').attr('disabled', true);
            $('#gbpOut').attr('disabled', true);
        });
        $('#eurOut').click(function(){
            $('#eurOut').attr('disabled', true);
            $('#eurOut').css("border-color","blue");
            var originalAmountNameL = 'eur';
            $('#tan').val(originalAmountNameL);
            $('#rurOut').attr('disabled', true);
            $('#usdOut').attr('disabled', true);
            $('#gbpOut').attr('disabled', true);
        });
        $('#gbpOut').click(function(){
            $('#gbpOut').attr('disabled', true);
            $('#gbpOut').css("border-color","blue");
            var originalAmountNameL = 'gbp';
            $('#tan').val(originalAmountNameL);
            $('#rurOut').attr('disabled', true);
            $('#usdOut').attr('disabled', true);
            $('#eurOut').attr('disabled', true);
        });

    });


//    Closing conversion form


    $('#conversion-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });


    $('#conversion').click(function()
        {
            var data = $('#conversion-form form').serialize();
            $.ajax({
                method: "POST",
                url: '/conversion/',
                data: data,
                success: function(response)
                {
                    $('#conversion-form').css('display', 'none');
                    var course = {};
                    conversion.id = response;
                    var dataArray = $('#conversion-form form').serializeArray();
                    for(i in dataArray) {
                        conversion[dataArray[i]['name']] = dataArray[i]['value'];
                    }
                    appendCourses(conversion);
                },
                error: function(response)
                {

                   if(response.status == 404) {
                       alert('Не хватает данных для конвертации');
                       $('ccc').click;
                       $('#conversion-form').css('display', 'none');
                       $('#conversion-out-form').css('display', 'none');
                       return false;
                   }
                }
            });
            $('#conversion-out-form').css('display', 'flex');
            $.ajax({
               method: "GET",
               url: '/conversion/out',
               success: function(response)
               {
                   var targetA = response;
                   $('#out').append('<div>' + targetA + '</div>');
               }
            });
            return false;
        });
});