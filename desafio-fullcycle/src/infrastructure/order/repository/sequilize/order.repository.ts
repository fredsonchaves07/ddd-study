import Order from "../../../../domain/checkout/entity/order";
import OrderItem from "../../../../domain/checkout/entity/order_item";
import OrderRepositoryInterface from "../../../../domain/checkout/repository/order-repository.interface";
import OrderItemModel from "./order-item.model";
import OrderModel from "./order.model";
export default class OrderRepository implements OrderRepositoryInterface {
  
  async update(entity: Order): Promise<void> {
    await OrderModel.update(
      {
        id: entity.id,
        customer_id: entity.customerId,
        total: entity.total(),
        items: entity.items.map((item) => ({
          id: item.id,
          name: item.name,
          price: item.price,
          product_id: item.productId,
          quantity: item.quantity,
        })),
      },
      {
        where: {
          id: entity.id,
        },
      }
    );
  }

  async find(id: string): Promise<Order> {
    let orderModel;
    try {
      orderModel = await OrderModel.findOne({
        where: { id },
        include: ["items"],
      });
  
    } catch (error) {
      throw new Error("Customer not found");
    }

    let items: OrderItem[];

    for(let i = 0; i <= orderModel.items.length; i ++) {
      let orderItem : OrderItem;
      orderItem = new OrderItem(
        orderModel.items[i].id,
        orderModel.items[i].name,
        orderModel.items[i].price,
        orderModel.items[i].product_id,
        orderModel.items[i].quantity
      )
      items.push(orderItem);
    }

  
    return new Order(
      orderModel.id,
      orderModel.customer_id,
      items
    )
  }

  async findAll(): Promise<Order[]> {
    const orderModels = await OrderModel.findAll();
    const orders = orderModels.map((orderModel) => {
      const oderItems =  orderModel.items.map((orderItemModel) => {
        let orderItem = new OrderItem(
          orderItemModel.id,
          orderItemModel.name,
          orderItemModel.price,
          orderItemModel.product_id,
          orderItemModel.quantity
        )
        return orderItem;
      })
      const order = new Order(
        orderModel.id,
        orderModel.customer_id,
        oderItems
      )
      return order;
    })
    return orders;
  }

  async create(entity: Order): Promise<void> {
    await OrderModel.create(
      {
        id: entity.id,
        customer_id: entity.customerId,
        total: entity.total(),
        items: entity.items.map((item) => ({
          id: item.id,
          name: item.name,
          price: item.price,
          product_id: item.productId,
          quantity: item.quantity,
        })),
      },
      {
        include: [{ model: OrderItemModel }],
      }
    );
  }
}
