import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Modal } from "react-bootstrap";
import StatusQuickAdd from "../Status/StatusQuickAdd";
import toast from "react-hot-toast";
import Select from "react-select";
import API from "../../Services/API";

export default class EditProject extends Component {
  constructor(props) {
    super(props);
    this.state = {
      api: new API(),
      statuses: [],
      programs: [],
      selectedStatus: "",
      selectedProgram: "",
      environmentName: this.props.match.params.name,
      environmentId: "",
      projectName: "",
      programId: "",
      statusId: "",
      projectId: this.props.match.params.id,
      showModal: false,
      showItemModal: false,
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.updateDate = this.updateDate.bind(this);
  }

  //HANDLE SUBMIT
  handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("projectName", this.state.projectName);
    formData.append("programId", this.state.programId);
    formData.append("statusId", this.state.statusId);
    await this.state.api.endpoints.project
      .update(formData, this.state.projectId)
      .then((response) => {
        toast.success("Project Edited Successfully!");
        this.setState({ projectId: response.data.projectId });
        this.props.history.push(
          `/environment/${this.state.environmentName}/project`
        );
      })
      .catch((error) => toast.error("Could not Edit Project"));
  };

  async componentDidMount() {
    this.state.api.createEntity({ name: "environment" });
    this.state.api.createEntity({ name: "program" });
    this.state.api.createEntity({ name: "status" });
    this.state.api.createEntity({ name: "project" });
    await this.state.api.endpoints.environment
      .getEnvironmentByName({ name: this.state.environmentName })
      .then((response) => {
        this.setState({ environmentId: response.data.environmentId });
      })
      .catch((error) => {
        this.props.history.push("/404");
      });

    await this.state.api.endpoints.program
      .getAll()
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
    await this.state.api.endpoints.status

      .getAll()
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

    await this.state.api.endpoints.project
      .getOne({ id: this.state.projectId })
      .then((response) => {
        this.setState({
          projectName: response.data.projectName,
          statusId: response.data.status.statusId,
          programId: response.data.program.programId,
        });
      })
      .catch((error) => {
        toast.error("Could not load Project");
      });
  }

  //TOGGLE ITEM MODAL
  handleItemModal() {
    this.setState({ showItemModal: !this.state.showItemModal });
  }

  //HANDLE INPUT CHANGE
  handleInputChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  //UPDATE DATES AFTER ADDING NEW STATUS
  async updateDate() {
    this.componentDidMount();
  }
  //TOGGLE MODAL
  handleModal() {
    this.setState({ showModal: !this.state.showModal });
  }

  render() {
    return (
      <div className="container">
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
            <li className="breadcrumb-item">
              <Link to={`/environment/${this.state.environmentName}/project`}>
                Projects
              </Link>
            </li>
            <li className="breadcrumb-item active" aria-current="page">
              Edit Project
            </li>
          </ol>
        </nav>
        <div className="jumbotron">
          <h3>Edit Project</h3>
          <form onSubmit={this.handleSubmit}>
            <div className="row">
              <div className="col-sm-12">
                <div className="form-row">
                  <div className="form-group col-md-12">
                    <label htmlFor="projectName">Name Project</label>
                    <input
                      type="text"
                      id="projectName"
                      name="projectName"
                      className="form-control"
                      placeholder="Name Project"
                      value={this.state.projectName}
                      onChange={this.handleInputChange}
                      required
                    />
                  </div>
                </div>
              </div>
              <div className="col-sm-6">
                <div className="form-row">
                  <div className="form-group col-md">
                    <label htmlFor="paceOfChange">Program</label>
                    <Select
                      value={this.state.programs.filter(
                        (program) => program.programId === this.state.programId
                      )}
                      options={this.state.programs}
                      name="program"
                      id="program"
                      placeholder="Add Program"
                      defaultValue={this.state.program}
                      onChange={(program) => {
                        this.setState({ programId: program.programId });
                      }}
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
                      value={this.state.statuses.filter(
                        (status) => status.statusId === this.state.statusId
                      )}
                      id="statusId"
                      name="statusId"
                      placeholder="Add Status"
                      options={this.state.statuses}
                      required
                      onChange={(status) => {
                        this.setState({ statusId: status.statusId });
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
