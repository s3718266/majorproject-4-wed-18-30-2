import React from 'react';
import '../App.css';
import { Button, Modal } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import config from '../Constants';
import Booking from './modals/booking' ;

class Dashboard extends React.Component {

  constructor(props) {
    super(props) //since we are extending class Table so we have to use super in order to override Component class constructor
    this.state = { //state is by default an object
      datas: [
        { id: 1, name: 'Hairdresser', provider: 'Hair Studio', price:"$10" },
        { id: 2, name: 'Lawn Mowner', provider: 'Simon & Co', price:"$15/h" },
        { id: 3, name: 'Dog Walker', provider: 'John', price:"$10/h" }
      ]
    }
  }
  renderTableData() {
    return this.state.datas.map((data, index) => {
      const { id, name, provider,price } = data
      return (
        <tr key={id}>
          <td>{id}</td>
          <td>{name}</td>
          <td>{provider}</td>
          <td>{price}</td>
        </tr>
      )
    })
  }

  renderTableHeader() {
    let header = Object.keys(this.state.datas[0])
    return header.map((key, index) => {
      return <th key={index}>{key.toUpperCase()}</th>
    })
  }







  render() {
    return (
      <div>
        <h1 id='title'>List of services</h1>
        <table id='datas'>
          <tbody>
            <tr>{this.renderTableHeader()}</tr>
            {this.renderTableData()}
          </tbody>
        </table>
        <br />

        <Booking />
      </div>
    )
  }

}

export default Dashboard;
