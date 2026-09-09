var cValidator = function () {
};

cValidator.prototype.ValidateTime24Hour = function (strTime, strSeperator) {
	var intLength = strTime.split(strSeperator).length -1;
	if(intLength == 1)	//HH:MM
		return !/^([01]?[0-9]|2[0-3]):[0-5][0-9]$/.test(strTime);
	else if(intLength == 2)	//HH:MM:SS
		return !/^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/.test(strTime);
};

cValidator.prototype.ValidateTime = function (strType, strValue) {
	if(strType == "MIN") {	//Minute validation
		return !/^([0-5]?[0-9]|60)$/.test(strValue);
	}
};

cValidator.prototype.ValidateDate = function (strValue, strDateRule) {
	if(strDateRule == "MM/dd/yyyy")
		return !/^(0\d|1[012])[- \/.](0\d|1\d|2\d|3[01])[- \/.](19|20)\d\d$/.test(strValue);
	else if(strDateRule == "dd-MMM-yyyy")
		return !/^(0\d|1\d|2\d|3[01])[- \/.]([a-zA-Z]{3})[- \/.](19|20)\d\d$/.test(strValue);
	return false;
};

var Validator = new cValidator();