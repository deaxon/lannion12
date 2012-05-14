class Annee < ActiveRecord::Base
  has_many :projects
  attr_accessible :year, :invisible_annee
  validates_uniqueness_of :year
  validates_presence_of :year
  validates_numericality_of :year, :greater_than_or_equal_to => 2000, :less_than_or_equal_to => 2050

end
