/**
 * 
 */

fi_utu_ville_exercises_hope_jsmap_JsMapStack = function(){
	var that, callback, slice = Array.prototype.slice;
	that = this;
	

	callback = function(){
		
		that.sendResults(slice.apply(arguments));
		
	};
	
	//maps(this.getElement(), this.getState().questions, this.getState().pointsPerCalc, callback);
};