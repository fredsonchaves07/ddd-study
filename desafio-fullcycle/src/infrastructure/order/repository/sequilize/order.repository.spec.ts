import { Sequelize } from "sequelize-typescript";
import Order from "../../../../domain/checkout/entity/order";
import OrderItem from "../../../../domain/checkout/entity/order_item";
import Customer from "../../../../domain/customer/entity/customer";
import Address from "../../../../domain/customer/value-object/address";
import Product from "../../../../domain/product/entity/product";
import CustomerModel from "../../../customer/repository/sequelize/customer.model";
import CustomerRepository from "../../../customer/repository/sequelize/customer.repository";
import ProductModel from "../../../product/repository/sequelize/product.model";
import ProductRepository from "../../../product/repository/sequelize/product.repository";
import OrderItemModel from "./order-item.model";
import OrderModel from "./order.model";
import OrderRepository from "./order.repository";

describe("Order repository test", () => {
  let sequelize: Sequelize;

  beforeEach( () => {
    sequelize = new Sequelize({
      dialect: "sqlite",
      storage: ":memory:",
      logging: false,
      sync: { force: true },
    });

     sequelize.addModels([
      CustomerModel,
      OrderModel,
      OrderItemModel,
      ProductModel,
    ]);
     sequelize.sync();
  });

  afterEach(async () => {
    await sequelize.close();
  });
  
  it("should create a new order", async () => {
    const customerRepository = new CustomerRepository();
    const customer = new Customer("123", "Customer 1");
    const address = new Address("Street 1", 1, "Zipcode 1", "City 1");
    customer.changeAddress(address);
    await customerRepository.create(customer);

    const productRepository = new ProductRepository();
    const product = new Product("123", "Product 1", 10);
    await productRepository.create(product);

    const orderItem = new OrderItem(
      "1",
      product.name,
      product.price,
      product.id,
      2
    );

    const order = new Order("123", "123", [orderItem]);

    const orderRepository = new OrderRepository();
    await orderRepository.create(order);

    const orderModel = await OrderModel.findOne({
      where: { id: order.id },
      include: ["items"],
    });

    expect(orderModel.toJSON()).toStrictEqual({
      id: "123",
      customer_id: "123",
      total: order.total(),
      items: [
        {
          id: orderItem.id,
          name: orderItem.name,
          price: orderItem.price,
          quantity: orderItem.quantity,
          order_id: "123",
          product_id: "123",
        },
      ],
    });
  });
});

it("should update a order", async () => {
  const customerRepository = new CustomerRepository();
  const customer = new Customer("123", "Customer 1");
  const address = new Address("Street 1", 1, "Zipcode 1", "City 1");
  customer.changeAddress(address);
  await customerRepository.create(customer);

  const productRepository = new ProductRepository();
  const product1 = new Product("123", "Product 1", 10);
  await productRepository.create(product1);

  const orderItem1 = new OrderItem(
    "1",
    product1.name,
    product1.price,
    product1.id,
    2
  );

  const order = new Order("123", "123", [orderItem1]);

  const orderRepository = new OrderRepository();
  await orderRepository.create(order);

  const product2 = new Product("351", "Product 2", 10);
  await productRepository.create(product2);

  const orderItem2 = new OrderItem(
    "2",
    product2.name,
    product2.price,
    product2.id,
    1
  );

  order.addItem(orderItem2);
  await orderRepository.update(order);

  const orderModel = await OrderModel.findOne({
    where: { id: order.id },
    include: ["items"],
  });

  expect(orderModel.toJSON()).toStrictEqual({
    id: "123",
    customer_id: "123",
    total: order.total(),
    items: [
      {
        id: orderItem1.id,
        name: orderItem1.name,
        price: orderItem1.price,
        quantity: orderItem1.quantity,
        order_id: "123",
        product_id: "123",
      },
      {
        id: orderItem2.id,
        name: orderItem2.name,
        price: orderItem2.price,
        quantity: orderItem2.quantity,
        order_id: "123",
        product_id: "351",
      }
    ],
  });
});

it("should find a order", async () => {
  const customerRepository = new CustomerRepository();
  const customer = new Customer("123", "Customer 1");
  const address = new Address("Street 1", 1, "Zipcode 1", "City 1");
  customer.changeAddress(address);
  await customerRepository.create(customer);

  const productRepository = new ProductRepository();
  const product = new Product("123", "Product 1", 10);
  await productRepository.create(product);

  const orderItem = new OrderItem(
    "1",
    product.name,
    product.price,
    product.id,
    2
  );

  const order = new Order("123", "123", [orderItem]);

  const orderRepository = new OrderRepository();
  await orderRepository.create(order);

  const orderResult = orderRepository.find(order.id);

  expect(order).toStrictEqual(orderResult);
});

it("should find all customers", async () => {
  /*
  const customerRepository = new CustomerRepository();
  const customer1 = new Customer("123", "Customer 1");
  const address1 = new Address("Street 1", 1, "Zipcode 1", "City 1");
  customer1.Address = address1;
  customer1.addRewardPoints(10);
  customer1.activate();

  const customer2 = new Customer("456", "Customer 2");
  const address2 = new Address("Street 2", 2, "Zipcode 2", "City 2");
  customer2.Address = address2;
  customer2.addRewardPoints(20);

  await customerRepository.create(customer1);
  await customerRepository.create(customer2);

  const customers = await customerRepository.findAll();

  expect(customers).toHaveLength(2);
  expect(customers).toContainEqual(customer1);
  expect(customers).toContainEqual(customer2);
  */

  const customerRepository = new CustomerRepository();
  const customer = new Customer("123", "Customer 1");
  const address = new Address("Street 1", 1, "Zipcode 1", "City 1");
  customer.changeAddress(address);
  await customerRepository.create(customer);

  const productRepository = new ProductRepository();
  const product = new Product("123", "Product 1", 10);
  await productRepository.create(product);

  const orderItem = new OrderItem(
    "1",
    product.name,
    product.price,
    product.id,
    2
  );

  const order = new Order("123", "123", [orderItem]);

  const orderRepository = new OrderRepository();
  await orderRepository.create(order);

  const orders = await orderRepository.findAll();
  
  expect(orders).toHaveLength(1);
  expect(orders).toContainEqual(order);
});