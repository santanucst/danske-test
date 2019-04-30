package com.app.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.app.dto.ElementDTO;
import com.app.dto.ResultPathDTO;

public class AppLogicService {

	public static ResultPathDTO traverse(List<List<ElementDTO>> recordList) {

		for (int rowIndx = 0; rowIndx < recordList.size() - 1; rowIndx++) {
			List<ElementDTO> elementList = recordList.get(rowIndx);
			List<ElementDTO> nxtElementList = recordList.get(rowIndx + 1);
			for (int colInxd = 0; colInxd < elementList.size(); colInxd++) {

				// Logic for proper non zero values
				if (elementList.get(colInxd).getElement() != 0) {
					// Logic for 1st element
					if (rowIndx == 0) {
						elementList.get(0).setSelected(true);
						elementList.get(0).setUpdatedElement(elementList.get(0).getElement());
						elementList.get(0).getSelectedElements().add(elementList.get(0).getElement());

						// Downward
						if (validateCriteria(elementList.get(0).getElement(), nxtElementList.get(0).getElement())) {
							nxtElementList.get(0).setSelected(true);
							nxtElementList.get(0).setUpdatedElement(
									elementList.get(0).getElement() + nxtElementList.get(0).getElement());
							nxtElementList.get(0).addSelectedElements(elementList.get(0).getSelectedElements());
						}

						// Diagonal
						if (validateCriteria(elementList.get(0).getElement(), nxtElementList.get(1).getElement())) {
							nxtElementList.get(1).setSelected(true);
							nxtElementList.get(1).setUpdatedElement(
									elementList.get(0).getElement() + nxtElementList.get(1).getElement());
							nxtElementList.get(1).addSelectedElements(elementList.get(0).getSelectedElements());
						}
					} // End - Logic for 1st element

					// Logic for other elements
					else {
						if (elementList.get(colInxd).getSelected()) {

							// Downward
							if (validateCriteria(elementList.get(colInxd).getElement(),
									nxtElementList.get(colInxd).getElement())) {
								Integer updatedValue = elementList.get(colInxd).getUpdatedElement()
										+ nxtElementList.get(colInxd).getElement();
								// Check for already Captured by largest series
								if (!(nxtElementList.get(colInxd).getSelected()
										&& nxtElementList.get(colInxd).getUpdatedElement() > updatedValue)) {
									nxtElementList.get(colInxd).setSelected(true);
									nxtElementList.get(colInxd).setUpdatedElement(updatedValue);
									nxtElementList.get(colInxd)
											.addSelectedElements(elementList.get(colInxd).getSelectedElements());
								}
							}

							// Diagonal
							if (validateCriteria(elementList.get(colInxd).getElement(),
									nxtElementList.get(colInxd + 1).getElement())) {
								Integer updatedValue = elementList.get(colInxd).getUpdatedElement()
										+ nxtElementList.get(colInxd + 1).getElement();
								// Check for already Captured by largest series
								if (!(nxtElementList.get(colInxd).getSelected()
										&& nxtElementList.get(colInxd).getUpdatedElement() > updatedValue)) {
									nxtElementList.get(colInxd + 1).setSelected(true);
									nxtElementList.get(colInxd + 1).setUpdatedElement(updatedValue);
									nxtElementList.get(colInxd + 1)
											.addSelectedElements(elementList.get(colInxd).getSelectedElements());
								}
							}

						}
					} // End - Logic for other elements
				} // End - Logic for proper non zero values
			}
		}

		System.out.println("");
		Set<ElementDTO> resultSet = new TreeSet<>();
		recordList.get(recordList.size() - 1).forEach(elm -> {
			System.out.print(elm.getElement() + "|");
			System.out.print(elm.getSelected() + "|");
			if (elm.getSelected()) {
				System.out.print(" Sum: " + elm.getUpdatedElement() + " | ");
				System.out.print(
						"Selected Elements(" + elm.getSelectedElements().size() + ") - " + elm.getSelectedElements());
				resultSet.add(elm);
			}
			System.out.println("");
		});
		System.out.println("");

		ElementDTO elm = resultSet.iterator().next();
		return new ResultPathDTO(elm.getUpdatedElement(), elm.getSelectedElements());
	}

	private static Boolean validateCriteria(Integer parent, Integer child) {
		if (parent % 2 == 0) {
			return child % 2 == 0 ? false : true;
		} else {
			return child % 2 == 0 ? true : false;
		}
	}

}
