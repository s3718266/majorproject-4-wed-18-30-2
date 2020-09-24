import React from 'react';
import '../App.css';
import { Button, Modal } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import config from '../Constants';
import Booking from './modals/booking';
import AddService from './modals/addService';

class Dashboard extends React.Component {

  constructor(props) {
    super(props)
  }

  getServices() {

    fetch(config.APP_URL + 'service/getall', {
      method: 'POST',
      body: ""
    })
      .then(res => res.json())
      .then(res => this.populateServices(res))

  }

  populateServices(res) {

    var parsedData = {};
    var index;
    var len;

    for (var k of Object.values(res)) {
      parsedData[k.id] = {
        "id": k.id,
        "name": k.name,
        "type": k.type,
        "workers": k.workers
      };
    }

    window.datas = parsedData;

    this.renderTableData();

  }

  renderTableData() {

    for (var k of Object.values(window.datas)) {

      const { id, type, name } = k

      document.getElementById('tblData').innerHTML += "<tr>" +
      "<td>" + id + "</td>" + 
      "<td>" + type + "</td>" + 
      "<td>" + name + "</td>" + 
      "</tr>";

    };

  }

  componentWillMount() {
    this.getServices();
  }

  render() {
    return (
      <div>
        <h1>CUSTOMER</h1>
        <h1 id='title'>List of services</h1>
        <table id='datas'>
          <tbody id="tblData">
            <tr>
              <th>ID</th>
              <th>Type</th>
              <th>Name</th>
            </tr>
          </tbody>
        </table>
        <br />

        <Booking />
        <br />
        <Button href="addService">Add Service</Button>
      </div>
    )
  }

}

export default Dashboard;
