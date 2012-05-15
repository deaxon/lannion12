class HomeController < ApplicationController
  def index
    @annees = Annee.all
  end
  def format
  end
  def project
  end
  def course
  end
end
