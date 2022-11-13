import React, { Component } from "react";
import Base from "../../components/Base";
class Table extends Component {

    constructor(props) {
        super(props)
        this.state = {
            orders: [],
            isLoading: false,
            isError: false
        }
        console.log("hii")

    }


    //asyn function get reuest to api

    async componentDidMount() {
        this.setState({ isLoading: true })

        const response = await fetch("http://localhost:8081/api/v1/order/findAllOrders");


        if (response.ok) {
            const orders = await response.json()
            this.orders = orders;

            console.log(this.state.orders)
            this.setState({ orders, isLoading: false })
        } else {
            this.setState({ isError: true, isLoading: false })
        }
    }
    renderTableHeader = () => {
        return (
            <tr>
                <th>Order ID</th>
                <th>Account</th>
                <th>DueDate</th>
                <th>Action</th>
            </tr>
        );

    }

    renderTableRows = () => {
        return this.state.orders.map(order => {
            return (
                <tbody>
                    <tr>
                        <td>{order.id}</td>
                        <td>{order.account}</td>
                        <td>{order.due_date}</td>
                        <td>
                            <button className="btn btn-primary" type="button" data-toggle="collapse" data-target={'#' + order.id} aria-expanded="false" aria-controls="collapseExample">
                                View Products
                            </button>
                        </td>

                    </tr>
                    <tr>
                        <td colSpan={4}>
                            <div className="collapse" id={order.id}>
                                <div className="card card-body">
                                    <table className="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Product ID</th>
                                                <th>Name</th>
                                                <th>Quantity</th>
                                                <th>Category</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {
                                                order.products.map(p => {
                                                    return (
                                                       
                                                        <tr>
                                                            <td>{p.pid}</td>
                                                            <td>{p.name}</td>
                                                            <td>{p.quantity}</td>
                                                            <td>{p.category}</td>
                                                        </tr>
                                                    )
                                                })

                                            }

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            )
        })

    }


    render() {
        const { orders, isLoading, isError } = this.state

        if (isLoading) {
            return <div>Loading....</div>
        }
        if (isError) {
            return <div>Error....</div>
        }
        return orders.length > 0 ? (
            <Base>
            <div className="container">

                <table className="table table-striped">
                    <thead>
                        {this.renderTableHeader()}
                    </thead>
                    {this.renderTableRows()}
                </table>
            </div>
            </Base>
        ) : (
            <div> No Orders... </div>

        )



    }

}
export default Table;