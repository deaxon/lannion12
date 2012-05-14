class Project < ActiveRecord::Base
  attr_accessible :projectName, :description, :shortDescription, :file, :pdf, :invisible
  belongs_to :annee
  belongs_to :attachable
  mount_uploader :file, FileUploader
  mount_uploader :pdf, PdfUploader

  has_many :students, :through =>  :trimester
  has_many :trimester
  has_many :teachers, :through => :semesters
  has_many :semesters

  validates_presence_of	:projectName 
  validates_uniqueness_of :projectName
  validates_presence_of	:description
  validates_presence_of	:shortDescription

end
