class ProjectsController < ApplicationController

  def show
    @project = Project.find(params[:id])
  end

  def index
    @projects = Project.all
  end

  def detail
  end

  def description
  end

  def files
  end

  def additionalDetails
  end

  def bugs
  end

end
