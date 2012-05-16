class Project < ActiveRecord::Base
  attr_accessible :projectName, :description, :shortDescription, :invisible, :uploads_attributes, :pdf_uploads_attributes
  belongs_to :annee

  has_many :uploads, :as => :attachable
  has_many :pdf_uploads,:as => :attachable

  accepts_nested_attributes_for :uploads
  accepts_nested_attributes_for :pdf_uploads

  has_many :students, :through =>  :trimester
  has_many :trimester
  has_many :teachers, :through => :semesters
  has_many :semesters

  validates_presence_of	:projectName 
  validates_uniqueness_of :projectName
  validates_presence_of	:description
  validates_presence_of	:shortDescription

end
