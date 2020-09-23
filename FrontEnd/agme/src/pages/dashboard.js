import React from 'react';
import '../App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import config from '../Constants';
import Booking from './modals/booking';

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
    this.renderWorkers();

  }

  renderTableData() {

    for (var k of Object.values(window.datas)) {

      const { id, type, name } = k

      if (window.selectedService == null) {
        window.selectedService = id;
      }

      document.getElementById('tblData').innerHTML += "<tr>" +
        "<td>" + id + "</td>" +
        "<td>" + type + "</td>" +
        "<td>" + name + "</td>" +
        "</tr>";

      document.getElementById('service').innerHTML += "<option id='" + id + "'>" + name + "</option>";

    };

  }

  handleService(e) {

    var select = document.getElementById('service');

    window.selectedService = select.options[select.selectedIndex];

  }

  renderWorkers() {

    for (var k of Object.values(window.datas)) {

      for (var g of Object.values(k.workers)) {

        document.getElementById('service').innerHTML += "<option id='" + g.id + "'>" + g.firstName + "</option>";

      };

    };

  }

  componentWillMount() {
    this.getServices();
  }

  render() {

    return (
      <div class="container">
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

        <Form className="login-form">

          <div className="alert alert-danger d-none" id="errorMessage">
          </div>

          <FormGroup>
            <Label>Date and Time</Label>
            <Input type="date" name="datetime" placeholder="Date and Time"></Input>
          </FormGroup>

          <FormGroup>
            <Label for="service">Services</Label>
            <Input type="select" name="service" id="service" onChange={this.handleService}>

            </Input>
          </FormGroup>

          <FormGroup>
            <Label for="worker">Worker</Label>
            <Input type="select" name="worker" id="worker">

            </Input>
          </FormGroup>

          <Button className="btn-lg btn-success btn-block mt-5 mb-3" type="submit">
            Book
        </Button>

        </Form>

      </div>

    )
  }

}

export default Dashboard;
