//lay tat ca san pham
axios({
  method: "get",
  url: "http://localhost:8080/product/list",
})
  .then(function (response) {
    return localStorage.setItem("products", JSON.stringify(response.data));
  })
  .catch((err) => console.log(err));

//mua hang
axios
  .post("http://localhost:8080/product/list/buy", {
    cart: JSON.parse(localStorage.getItem("items")),//gio hang hien tai
  })
  .then(function (response) {
    console.log(response);
  })
  .catch((err) => console.log(err));

const checkExist = (currentCart, product) => {
  if (currentCart?.find((item) => item.id === product.id)) {
    return true;
  }
};
const cart = [];

const products = JSON.parse(localStorage.getItem("products"));

console.log("products", products);

const addToCart = (id) => {
  currentCart = JSON.parse(localStorage.getItem("items"));
  products.find((product) => {
    if (product.id === Number(id)) {
      if (checkExist(currentCart, product)) {
        return;
      }
      cart.push({
        ...product,
        quantity: 1,
      });
    }
  });
  localStorage.setItem("items", JSON.stringify(cart));
};

const findProduct = (id) => {
  return products.find((product) => product.id === id);
};

const renderItemInCart = (cart) => {
  cart?.map(
    (product) =>
      (document.getElementById("renderItem").innerHTML += `
      <div class="row mb-4 d-flex justify-content-between align-items-center">
  <div class="col-md-2 col-lg-2 col-xl-2">
    <img
      src="/img/${product.imgUrl}"
      class="img-fluid rounded-3"
      alt="Cotton T-shirt"
    />
  </div>
  <div class="col-md-3 col-lg-3 col-xl-3">
    <h6 class="text-muted">${product.productType.description}</h6>
    <h6 class="text-black mb-0">${product.name}</h6>
  </div>

  <div class="col-md-3 col-lg-3 col-xl-2 d-flex" style="align-items: center;
    justify-content: center;">
    <button class="btn btn-link px-2" onclick="deincrease(${product.id})">
      <i class="fas fa-minus"></i>
    </button>
    
    <input id="form${
      product.id
    }"  name="quantity" value="1" type="text" class="form-control form-control-sm" style="width: 80px;"></input>

    <button class="btn btn-link px-2" onclick="increase(${
      findProduct(product.id).quantity
    },${product.id})">
      <i class="fas fa-plus"></i>
    </button>
  </div>
  
  <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
    <h6 class="mb-0">$${product.price * 3}</h6>
  </div>
  <div class="col-md-1 col-lg-1 col-xl-1 text-end">
    <a href="#!" class="text-muted">
      <i class="fas fa-times"></i>
    </a>
  </div>
</div>

<hr class="my-4" />
`)
  );
};

if (document.getElementById("renderItem")) {
  console.log("get item: ", JSON.parse(localStorage.getItem("items")));
  renderItemInCart(JSON.parse(localStorage.getItem("items")));
}
const increase = (quantity, id) => {
  let inputId = document.querySelector(`#form${id}`);
  currentQuantity = Number(inputId.value);
  let itemQuantity = currentQuantity;
  if (itemQuantity < quantity) {
    itemQuantity += 1;
  }
  inputId.setAttribute("value", itemQuantity);
  const cartTemp = JSON.parse(localStorage.getItem("items"));
  let temp = undefined;
  cartTemp.map((item) => {
    if (item.id === id) {
      item.quantity = itemQuantity;
    }
    localStorage.setItem("items", JSON.stringify(cartTemp));
  });
};

const deincrease = (id) => {
  let inputId = document.querySelector(`#form${id}`);
  currentQuantity = Number(inputId.value);
  let itemQuantity = currentQuantity;
  Number(itemQuantity) === 1 && Number(itemQuantity) > 0
    ? (itemQuantity = 1)
    : (itemQuantity -= 1);
  inputId.setAttribute("value", itemQuantity);
  const cartTemp = JSON.parse(localStorage.getItem("items"));
  let temp = undefined;
  cartTemp.map((item) => {
    if (item.id === id) {
      item.quantity = itemQuantity;
    }
    localStorage.setItem("items", JSON.stringify(cartTemp));
  });
};
