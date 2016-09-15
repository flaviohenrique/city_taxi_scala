package services

import javax.inject.Inject

import repositories.TaxiRepository
import forms.CreateTaxiForm
import models.Taxi

class TaxiService @Inject() (repository: TaxiRepository) {

  def create(form: CreateTaxiForm) = {
    repository.create(Taxi.create(form.name, form.row, form.col, form.map_id))

  }
}