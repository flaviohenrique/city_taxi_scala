package services

import javax.inject.Inject

import forms.CreatePassengerForm
import models.Passenger
import repositories.PassengerRepository

class PassengerService @Inject() (repository: PassengerRepository) {

  def create(form: CreatePassengerForm) = {
    repository.create(Passenger.create(form.name, form.row, form.col, form.dest_row, form.dest_col, form.map_id))
  }
}