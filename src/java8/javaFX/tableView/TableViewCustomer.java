package java8.javaFX.tableView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TableViewCustomer extends Application {

	;
	private final TableView<Customer1> table = createTable();
	private final List<Customer1> data = Customer1.readCVS();
	private final static int rowsPerPage = 30;

	@Override
	public void start(Stage arg0) throws Exception {
		Pagination pagination = new Pagination((data.size() / rowsPerPage + 1),
				0);
		pagination.setPageFactory(this::createPage);

		Scene scene = new Scene(new BorderPane(pagination), 1024, 768);
		table.setEditable(true);
		arg0.setScene(scene);
		arg0.setTitle("Customer data");
		arg0.show();

	}

	/***
	 * Return the output as a tableview
	 * 
	 * @return {@link TableView}
	 */
	private TableView<Customer1> createTable() {
		TableView<Customer1> table = new TableView<>();

		table.setEditable(true);
		TableColumn<Customer1, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(param -> param.getValue().getId());
		colId.setPrefWidth(100);

		TableColumn<Customer1, String> colFname = new TableColumn<>(
				"First Name");
		colFname.setCellValueFactory(param -> param.getValue().getFirst_name());
		colFname.setPrefWidth(250);
		colFname.setCellFactory(TextFieldTableCell.forTableColumn());
		colFname.setOnEditCommit(new EventHandler<CellEditEvent<Customer1, String>>() {
			@Override
			public void handle(CellEditEvent<Customer1, String> t) {
				((Customer1) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setFirst_name(t
						.getNewValue());
			}
		});

		TableColumn<Customer1, String> colLname = new TableColumn<>("Last Name");
		colLname.setCellValueFactory(param -> param.getValue().getLast_name());
		colLname.setPrefWidth(250);
		colLname.setCellFactory(TextFieldTableCell.forTableColumn());
		colLname.setOnEditCommit(new EventHandler<CellEditEvent<Customer1, String>>() {
			@Override
			public void handle(CellEditEvent<Customer1, String> t) {
				((Customer1) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setLast_name(t
						.getNewValue());
			}
		});

		TableColumn<Customer1, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(param -> param.getValue().getEmail());
		colEmail.setPrefWidth(250);
		colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		colEmail.setOnEditCommit(new EventHandler<CellEditEvent<Customer1, String>>() {
			@Override
			public void handle(CellEditEvent<Customer1, String> t) {
				((Customer1) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setEmail(t
						.getNewValue());
			}
		});

		TableColumn<Customer1, String> colCountry = new TableColumn<>("Country");
		colCountry.setCellValueFactory(param -> param.getValue().getCountry());
		colCountry.setPrefWidth(250);
		colCountry.setCellFactory(TextFieldTableCell.forTableColumn());
		colCountry
				.setOnEditCommit(new EventHandler<CellEditEvent<Customer1, String>>() {
					@Override
					public void handle(CellEditEvent<Customer1, String> t) {
						((Customer1) t.getTableView().getItems()
								.get(t.getTablePosition().getRow()))
								.setCountry(t.getNewValue());
					}
				});

		TableColumn<Customer1, String> colCompany = new TableColumn<>("Company");
		colCompany.setCellValueFactory(param -> param.getValue().getCompany());
		colCompany.setPrefWidth(250);
		colCompany.setCellFactory(TextFieldTableCell.forTableColumn());
		colCompany
				.setOnEditCommit(new EventHandler<CellEditEvent<Customer1, String>>() {
					@Override
					public void handle(CellEditEvent<Customer1, String> t) {
						((Customer1) t.getTableView().getItems()
								.get(t.getTablePosition().getRow()))
								.setCompany(t.getNewValue());
					}
				});

		TableColumn<Customer1, String> colFlag = new TableColumn<>("Flag");
		colFlag.setCellValueFactory(param -> param.getValue().getFlag());
		colFlag.setPrefWidth(100);
		colFlag.setCellFactory(TextFieldTableCell.forTableColumn());
		colFlag.setOnEditCommit(new EventHandler<CellEditEvent<Customer1, String>>() {
			@Override
			public void handle(CellEditEvent<Customer1, String> t) {
				((Customer1) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setFlag(t
						.getNewValue());
			}
		});

		table.getColumns().addAll(colId, colFname, colLname, colEmail,
				colCountry, colCompany, colFlag);
		return table;
	}

	private Node createPage(int pageIndex) {

		int fromIndex = pageIndex * rowsPerPage;
		int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
		table.setItems(FXCollections.observableArrayList(data.subList(
				fromIndex, toIndex)));

		return new BorderPane(table);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static class Customer1 {
		private ObservableValue<Integer> id;
		private SimpleStringProperty first_name;
		private SimpleStringProperty last_name;
		private SimpleStringProperty email;
		private SimpleStringProperty country;
		private SimpleStringProperty company;
		private SimpleStringProperty flag;

		public Customer1(int id, String first_name, String last_name,
				String email, String country, String company, String flag) {
			this.id = new SimpleObjectProperty<>(id);
			this.first_name = new SimpleStringProperty(first_name);
			this.last_name = new SimpleStringProperty(last_name);
			this.email = new SimpleStringProperty(email);
			this.country = new SimpleStringProperty(country);
			this.company = new SimpleStringProperty(company);
			this.flag = new SimpleStringProperty(flag);
		}

		public ObservableValue<Integer> getId() {
			return id;
		}

		public SimpleStringProperty getFirst_name() {
			return first_name;
		}

		public SimpleStringProperty getLast_name() {
			return last_name;
		}

		public SimpleStringProperty getEmail() {
			return email;
		}

		public SimpleStringProperty getCountry() {
			return country;
		}

		public SimpleStringProperty getCompany() {
			return company;
		}

		public SimpleStringProperty getFlag() {
			return flag;
		}

		public void setFirst_name(final String first_name) {
			this.first_name = new SimpleStringProperty(first_name);
		}

		public void setLast_name(String last_name) {
			this.last_name = new SimpleStringProperty(last_name);
		}

		public void setEmail(String email) {
			this.email = new SimpleStringProperty(email);
		}

		public void setCountry(String country) {
			this.country = new SimpleStringProperty(country);
		}

		public void setCompany(String company) {
			this.company = new SimpleStringProperty(company);
		}

		public void setFlag(String flag) {
			this.flag = new SimpleStringProperty(flag);
		}

		@Override
		public String toString() {
			return "Customer1 [id=" + id + ", first_name=" + first_name
					+ ", last_name=" + last_name + ", email=" + email
					+ ", country=" + country + ", company=" + company
					+ ", flag=" + flag + "]";
		}

		/***
		 * Read the csv file
		 * 
		 * @return {@link List}
		 */
		public static List<Customer1> readCVS() {
			List<Customer1> customers = new ArrayList<Customer1>();
			String csvFile = "data/MOCK_DATA.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";

			try {
				int iteration = 0;
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					if (iteration == 0) {
						iteration++;
						continue;
					}

					// define separator
					String[] customer = line.split(cvsSplitBy);

					int id = Integer.parseInt(customer[0]);
					customers
							.add(new Customer1(id, customer[1], customer[2],
									customer[3], customer[4], customer[5],
									customer[6]));

				}

			} catch (FileNotFoundException e) {
				System.out.println("Could not find " + csvFile.toString());
			} catch (IOException e) {
				System.out.println("Unable to read the file "
						+ csvFile.toString());
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			System.out.println("reading file successfully");
			return customers;
		}

	}

}