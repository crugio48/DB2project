(function() { //IIFE to avoid variables ending up in the global scope

    var mobilePhone, mobileInternet, fixedInternet, validityPeriod,
        pageOrchestrator = new PageOrchestrator();


    window.addEventListener("load", () => {
        pageOrchestrator.start();
        pageOrchestrator.refresh();
    }, false);


    function MobilePhone(counter, div) {
        this.counter = counter;
        this.myDiv = div;

        this.reset = function() {
            //empties previes html tags created
            this.myDiv.innerHTML = "";

        };

        this.show = function(number) {
            
            var input, span, br, inputDiv;

            for (var i = 1; i <= number; i++){

                //printing Mobile phone i
                span = document.createElement("span");

                span.textContent = "Mobile phone " + i.toString() + " attributes:";

                this.myDiv.appendChild(span);

                br = document.createElement("br");

                this.myDiv.appendChild(br);

                //creating input for number of minutes
                inputDiv = document.createElement("div");

                inputDiv.textContent = "Number of minutes: ";

                input = document.createElement("input");

                input.type = "number";
                input.min = "0";
                input.name = "mobile_phone_minutes" + i.toString();

                inputDiv.appendChild(input);

                this.myDiv.appendChild(inputDiv);

                //creating input for number of sms
                inputDiv = document.createElement("div");

                inputDiv.textContent = "Number of sms: ";

                input = document.createElement("input");

                input.type = "number";
                input.min = "0";
                input.name = "mobile_phone_sms" + i.toString();

                inputDiv.appendChild(input);

                this.myDiv.appendChild(inputDiv);

                //creating input for fee for extra minutes
                inputDiv = document.createElement("div");

                inputDiv.textContent = "Fee for every extra minute: ";

                input = document.createElement("input");

                input.type = "number";
                input.min = "0";
                input.step = "0.01";
                input.name = "mobile_phone_fee_minutes" + i.toString();

                inputDiv.appendChild(input);

                this.myDiv.appendChild(inputDiv);

                //creating input for fee for extra sms
                inputDiv = document.createElement("div");

                inputDiv.textContent = "Fee for every extra sms: ";

                input = document.createElement("input");

                input.type = "number";
                input.min = "0";
                input.step = "0.01"
                input.name = "mobile_phone_fee_sms" + i.toString();

                inputDiv.appendChild(input);

                this.myDiv.appendChild(inputDiv);

                //extra space
                this.myDiv.appendChild(br);
            }

        };


        counter.addEventListener("change" , (e) => {
            var number = e.target.value;

            if (number < 0){
                this.reset();
                var error = document.createElement("div");
                error.textContent = "you can't specify a negative number"
                this.myDiv.appendChild(error);
            }
            else {
                this.reset();
                this.show(number);
            }
        }, false);



    }// no ; at the end of this function because it is not a function expression


    function MobileInternet(counter, div) {
        this.counter = counter;
        this.myDiv = div;

        this.reset = function(){
            //empties previes html tags created
            this.myDiv.innerHTML = "";
        };
            
        this.show = function(number){
            var span, br, inputNumGiga, inputFieldForGiga, inputFeeExtraGiga, inputFieldFeeExtraGiga;
            
            for (var i = 1; i <= number; i++){
                //creating Mobile Internet i
                span = document.createElement("span");
                span.textContent = "Mobile Internet " + i.toString() + " attributes:"
                this.myDiv.appendChild(span);
                
                br = document.createElement("br");
                this.myDiv.appendChild(br);
                
                //creating input for gigabytes
                inputNumGiga = document.createElement("div");
                inputNumGiga.textContent = "Number of Gigabytes: ";
                inputFieldForGiga = document.createElement("input");
                inputFieldForGiga.type = "number";
                inputFieldForGiga.min = "0";
                inputFieldForGiga.name = "mobile_gigabytes" + i.toString();
                
                inputNumGiga.appendChild(inputFieldForGiga);
                
                this.myDiv.appendChild(inputNumGiga);
                
                //creating input for fee for extra gigabytes
                inputFeeExtraGiga = document.createElement("div");
                inputFeeExtraGiga.textContent = "Fee for every extra gigabyte: ";
                inputFieldFeeExtraGiga = document.createElement("input");
                inputFieldFeeExtraGiga.type = "number";
                inputFieldFeeExtraGiga.min = "0";
                inputFieldFeeExtraGiga.step = "0.01"
                inputFieldFeeExtraGiga.name = "mobile_fee_gigabytes" + i.toString();
                
                inputFeeExtraGiga.appendChild(inputFieldFeeExtraGiga);

                this.myDiv.appendChild(inputFeeExtraGiga);
                
                this.myDiv.appendChild(br);
            
            }
        };
            
        counter.addEventListener("change", (e) => {
            var number = e.target.value;
            
            if (number < 0 ){
                this.reset();
                var error = document.createElement("div");
                error.textContent = "you can't specify a negative number"
                this.myDiv.appendChild(error);
            }
            else {
                this.reset();
                this.show(number);
            }
        }, false);

    }// no ; at the end of this function because it is not a function expression

    function FixedInternet(counter, div) {
        this.counter = counter;
        this.myDiv = div;

        this.reset = function() {
            //empties previes html tags created
            this.myDiv.innerHTML = "";
        };

        this.show = function(number) {
            var span, br, inputNumGiga, inputFieldForGiga, inputFeeExtraGiga, inputFieldFeeExtraGiga;
            
            for (var i = 1; i <= number; i++){
                //creating Fixed Internet i
                span = document.createElement("span");
                span.textContent = "Fixed Internet " + i.toString() + " attributes:"
                this.myDiv.appendChild(span);
                
                br = document.createElement("br");
                this.myDiv.appendChild(br);
                
                //creating input for gigabytes
                inputNumGiga = document.createElement("div");
                inputNumGiga.textContent = "Number of Gigabytes: ";
                inputFieldForGiga = document.createElement("input");
                inputFieldForGiga.type = "number";
                inputFieldForGiga.min = "0";
                inputFieldForGiga.name = "fixed_gigabytes" + i.toString();
                
                inputNumGiga.appendChild(inputFieldForGiga);
                
                this.myDiv.appendChild(inputNumGiga);
                
                //creating input for fee for extra gigabytes
                inputFeeExtraGiga = document.createElement("div");
                inputFeeExtraGiga.textContent = "Fee for every extra gigabyte: ";
                inputFieldFeeExtraGiga = document.createElement("input");
                inputFieldFeeExtraGiga.type = "number";
                inputFieldFeeExtraGiga.min = "0";
                inputFieldFeeExtraGiga.step = "0.01"
                inputFieldFeeExtraGiga.name = "fixed_fee_gigabytes" + i.toString();
                
                inputFeeExtraGiga.appendChild(inputFieldFeeExtraGiga);

                this.myDiv.appendChild(inputFeeExtraGiga);
                
                this.myDiv.appendChild(br);
            
            }
        };

        counter.addEventListener("change", (e) => {
            var number = e.target.value;
            
            if (number < 0 ){
                this.reset();
                var error = document.createElement("div");
                error.textContent = "you can't specify a negative number"
                this.myDiv.appendChild(error);
            }
            else {
                this.reset();
                this.show(number);
            }
        }, false);

    }// no ; at the end of this function because it is not a function expression


    function ValidityPeriod(counter, div) {
        this.counter = counter;
        this.myDiv = div;

        this.reset = function(){
            //empties previes html tags created
            this.myDiv.innerHTML = "";
        }
    
        this.show = function(number){
            var span, br, 
              inputValidityPeriodLength, inputFieldValidityPeriodLength, 
              inputValidityPeriodCost, inputFieldValidityPeriodCost;
    
            for (var i = 1; i <= number; i++){
                span = document.createElement("span");
                span.textContent = "Validity Period " + i.toString() + " attributes:"
                this.myDiv.appendChild(span);
    
                br = document.createElement("br");
                this.myDiv.appendChild(br);
    
                inputValidityPeriodLength = document.createElement("div");
                inputValidityPeriodLength.textContent = "Duration in months of the validity Period: ";
                inputFieldValidityPeriodLength = document.createElement("input");
                    inputFieldValidityPeriodLength.type = "number";
                    inputFieldValidityPeriodLength.min = "1";
                    inputFieldValidityPeriodLength.name = "validity_period_duration" + i.toString();
    
                inputValidityPeriodLength.appendChild(inputFieldValidityPeriodLength);
    
                this.myDiv.appendChild(inputValidityPeriodLength);
    
    
                inputValidityPeriodCost = document.createElement("div");
                inputValidityPeriodCost.textContent = "Cost for the validity period per month: ";
                inputFieldValidityPeriodCost = document.createElement("input");
                    inputFieldValidityPeriodCost.type = "number";
                    inputFieldValidityPeriodCost.min = "0";
                    inputFieldValidityPeriodCost.step = "0.01";
                    inputFieldValidityPeriodCost.name = "validity_period_cost" + i.toString();
                
                inputValidityPeriodCost.appendChild(inputFieldValidityPeriodCost);
                
                this.myDiv.appendChild(inputValidityPeriodCost);
                
                this.myDiv.appendChild(br);
    
            }
        }
    
        counter.addEventListener("change", (e) => {
            var number = e.target.value;
    
            if (number < 1 ){
                this.reset();
                var error = document.createElement("div");
                error.textContent = "you must at least specify 1 validity period"
                this.myDiv.appendChild(error);
            }
            else {
                this.reset();
                this.show(number);
            }
        }, false);

    }// no ; at the end of this function because it is not a function expression

    function PageOrchestrator() {

        this.start = function(){

            mobilePhone = new MobilePhone(
                document.getElementById("mobilePhonesCounter"),
                document.getElementById("mobilePhoneDiv")
            );

            mobileInternet = new MobileInternet(
                document.getElementById("mobileInternetCounter"),
                document.getElementById("mobileInternetDiv")
            );

            fixedInternet = new FixedInternet(
                document.getElementById("fixedInternetCounter"),
                document.getElementById("fixedInternetDiv")
            );

            validityPeriod = new ValidityPeriod(
                document.getElementById("validityPeriodCounter"),
                document.getElementById("validityPeriodDiv")
            );

        };


        this.refresh = function() {
            
            mobilePhone.reset();
            mobileInternet.reset();
            fixedInternet.reset();
            validityPeriod.reset();

        };

    } // no ; at the end of this function because it is not a function expression


})(); // end of IIFE