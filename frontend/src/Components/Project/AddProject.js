import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { Modal } from "react-bootstrap";
import StatusQuickAdd from "../Status/StatusQuickAdd";
import toast from "react-hot-toast";
import Select from "react-select";

export default class AddProject extends Component {
  constructor(props) {
    super(props);
    this.state = {
      statuses: [],
      programs: [],

      selectedStatus: "",
      selectedProgram: "",

      environmentName: this.props.match.params.name,
      environmentId: "",
      projectId: "",
      showModal: false,
      showItemModal: false,
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.updateDate = this.updateDate.bind(this);
  }

  handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("programId", this.state.selectedProgram.programId);
    formData.append("statusId", this.state.selectedStatus.statusId);
    console.log(this.state.selectedProgram.programId);
    console.log(this.state.selectedStatus.statusId);
    await axios
      .post(`${process.env.REACT_APP_API_URL}/project/`, formData)
      .then((response) => {
        toast.success("Project Added Successfully!");
        this.setState({ projectId: response.data.projectId });
        this.props.history.push(
          `/environment/${this.state.environmentName}/project`
        );
      })
      .catch((error) => toast.error("Could not Add Project"));
  };

  async componentDidMount() {
    await axios
      .get(
        `${process.env.REACT_APP_API_URL}/environment/environmentname/${this.state.environmentName}`
      )
      .then((response) => {
        this.setState({ environmentId: response.data.environmentId });
      })
      .catch((error) => {
        console.log(error);
        this.props.history.push("/notfounderror");
      });

    await axios
      .get(`${process.env.REACT_APP_API_URL}/program/`)
      .then((response) => {
        response.data.forEach((program) => {
          program.label = program.programName;
          program.value = program.programId;
        });
        this.setState({ programs: response.data });
      })
      .catch((error) => {
        toast.error("Could not load Programs");
      });
    await axios
      .get(`${process.env.REACT_APP_API_URL}/status/`)
      .then((response) => {
        response.data.forEach((status) => {
          status.label = status.validityPeriod;
          status.value = status.statusId;
        });
        this.setState({ statuses: response.data });
      })
      .catch((error) => {
        toast.error("Could not load Statuses");
      });
  }

  handleItemModal() {
    this.setState({ showItemModal: !this.state.showItemModal });
  }

  handleInputChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  async updateDate() {
    this.componentDidMount();
  }

  handleModal() {
    this.setState({ showModal: !this.state.showModal });
  }

  render() {
    return (
      <div>
        <br></br>
        <nav aria-label="breadcrumb">
          <ol className="breadcrumb">
            <li className="breadcrumb-item">
              <Link to={`/`}>Home</Link>
            </li>
            <li className="breadcrumb-item">
              <Link to={`/environment/${this.state.environmentName}`}>
                {this.state.environmentName}
              </Link>
            </li>
            <li className="breadcrumb-item active" aria-current="page">
              Add Project
            </li>
          </ol>
        </nav>
        <div className="jumbotron">
          <h3>Add Project</h3>
          <form onSubmit={this.handleSubmit}>
            <div className="row">
              <div className="col-sm-6">
                <div className="form-row">
                  <div className="form-group col-md">
                    <label htmlFor="paceOfChange">Program</label>
                    <Select
                      options={this.state.programs}
                      name="program"
                      id="program"
                      placeholder="Add Program"
                      value={this.state.programId}
                      onChange={(program) =>
                        this.setState({ selectedProgram: program })
                      }
                      required
                    ></Select>
                  </div>
                </div>
              </div>
              <div className="col-sm-6">
                <div className="form-row">
                  <div className="form-group col-md-9">
                    <label htmlFor="statusId">Status</label>
                    <Select
                      id="statusId"
                      name="statusId"
                      placeholder="Validity Period"
                      value={this.state.statusId}
                      options={this.state.statuses}
                      required
                      onChange={(statusId) => {
                        this.setState({ selectedStatus: statusId });
                      }}
                    ></Select>
                    <Modal show={this.state.showModal}>
                      <Modal.Header>Add Status</Modal.Header>
                      <Modal.Body>
                        <StatusQuickAdd
                          environmentName={this.state.environmentName}
                          updateDate={this.updateDate}
                        />
                      </Modal.Body>
                      <Modal.Footer>
                        <button
                          type="button"
                          className="btn btn-secondary"
                          onClick={() => this.handleModal()}
                        >
                          Close Modal
                        </button>
                      </Modal.Footer>
                    </Modal>
                  </div>
                  <button
                    type="button"
                    className="btn btn-secondary"
                    style={{ height: 40, marginTop: 30 }}
                    onClick={() => this.handleModal()}
                  >
                    Add Status
                  </button>
                </div>
              </div>
            </div>
            <button
              className="btn btn-primary"
              type="button"
              onClick={this.handleSubmit}
            >
              Submit
            </button>
          </form>
        </div>
      </div>
    );
  }
}