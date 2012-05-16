class ProjectsController < ApplicationController
  before_filter :authorize, :except => [:index, :show]
  before_filter :get_annee

  def roll
    @project = Project.find(params[:id])
  end

  def publier
    @project = @annee.projects.find(params[:id])
    @project.invisible = FALSE
    @project.save
    redirect_to(annee_projects_path(@annee))
  end

  def horsligne
    @project = @annee.projects.find(params[:id])
    @project.invisible = TRUE
    @project.save
    redirect_to(annee_projects_path(@annee))
  end

  def index
    @projects = @annee.projects.all
    @annees = Annee.all
    respond_to do |format|
      format.html # index.html.erb
      format.xml { render :xml => @projects }
    end
  end

  def show
    @project = @annee.projects.find(params[:id])
    respond_to do |format|
      format.html # show.html.erb
      format.xml { render :xml => @project }
    end
  end

  def new
    @annee = Annee.find(params[:annee_id])
    @project = @annee.projects.build
    respond_to do |format|
      format.html # index.html.erb
      format.xml { render :xml => @projects }
    end
  end

  def edit
    @project = @annee.projects.find(params[:id])
  end

  def create
    @project = @annee.projects.build(params[:project])
    respond_to do |format|
      if @project.save
        flash[:notice] = 'Le projet a ete cree.'
        format.html { redirect_to([@annee, @project]) }
        format.xml { render :xml => @project, :status => :created, :location => @project }
      else
        format.html { render :action => "new" }
        format.xml { render :xml => @project.errors, :status => :unprocessable_entity }
      end
    end
  end

  def update
    @project = @annee.projects.find(params[:id])
    if params[:project][:uploads_attributes]
      params[:project][:uploads_attributes].each_key { |key|
        if params[:project][:uploads_attributes][key.to_sym][:_destroy]=="1"
          @upload = Upload.find(params[:project][:uploads_attributes][key.to_sym][:id])
          @upload.destroy
          params[:project][:uploads_attributes].delete(key.to_sym)
        end
      }
    end
    if params[:project][:pdf_uploads_attributes]
      params[:project][:pdf_uploads_attributes].each_key { |key|
        if params[:project][:pdf_uploads_attributes][key.to_sym][:_destroy]=="1"
          @pdf_upload = PdfUpload.find(params[:project][:pdf_uploads_attributes][key.to_sym][:id])
          @pdf_upload.destroy
          params[:project][:pdf_uploads_attributes].delete(key.to_sym)
        end
      }
    end
    respond_to do |format|
      if @project.update_attributes(params[:project])
        flash[:notice] = 'Le projet a ete mis a jour.'
        format.html { redirect_to([@annee, @project]) }
        format.xml { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml { render :xml => @project.errors, :status => :unprocessable_entity }
      end
    end
  end

  def destroy
    @project = @annee.projects.find(params[:id])
    @uploads = Upload.where(:attachable_id => @project.id)
    @uploads.destroy(@uploads)
    @pdf_uploads = PdfUpload.where(:attachable_id => @project.id)
    @pdf_uploads.destroy(@pdf_uploads)
    @project.destroy

    flash[:notice] = 'Le projet a ete detruit.'

    respond_to do |format|
      format.html { redirect_to(annee_projects_path(@annee)) }
      format.xml { head :ok }
    end
  end

  private
  def get_annee
    @annee = Annee.find(params[:annee_id])
  end

end
