const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {

	//Quản lý giỏ hàng
	$scope.cart = {
		items:[],
		//Thêm sản phẩm vào giỏ hàng
		add(id) {
			var item=this.items.find(item =>item.id == id)
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			}else{
				$http.get(`/oneshop/rest/products/${id}`).then(resp =>{
					resp.data.qty=1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		//Lưu vào giỏ hàng
		saveToLocalStorage(){
			var json=JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart",json);
		}}
});