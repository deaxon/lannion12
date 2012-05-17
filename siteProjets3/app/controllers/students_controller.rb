class StudentsController < ApplicationController
  # GET /students
  # GET /students.json
  before_filter :authorize
  def projects
    @student = Student.find(params[:id])
    @projects = @student.projects
    @trimesters = Trimester.find(:all, :conditions => { :student_id => @student, :project_id => @projects})
  end

  def publier
    @student = Student.find(params[:id])
    @student.inactif = FALSE
    @student.save
    redirect_to(students_path)
  end

  def horsligne
    @student = Student.find(params[:id])
    @student.inactif = TRUE
    @student.save
    redirect_to(students_path)
  end

  def project_add
    @student = Student.find(params[:id])
    @project = Project.find(params[:project])


    unless @student.enrolled_in?(@project)
      @student.projects << @project

      @semester = Trimester.find(:last)
      @sem = params[:semester]
      @semester.semester = @sem
      @semester.save

      flash[:notice] = 'Projet ajoute.'
    else
      flash[:notice] = 'Etudiant deja dans le projet !'
    end
    redirect_to :action => :projects, :id => @student
  end

  def project_remove
    @student = Student.find(params[:id])
    project_ids = params[:projects]

    unless project_ids.blank?
      project_ids.each do |project_id|
        project = Project.find(project_id)
        if @student.enrolled_in?(project)
          logger.info "Enlever l'etudiant du projet #{project.id}"
          @student.projects.delete(project)
          flash[:notice] = 'Projet enleve.'
        end
      end
    end
    redirect_to :action => :projects, :id => @student
  end

  def index
    @students = Student.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @students }
    end
  end

  # GET /students/1
  # GET /students/1.json
  def show
    @student = Student.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @student }
    end
  end

  # GET /students/new
  # GET /students/new.json
  def new
    @student = Student.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @student }
    end
  end

  # GET /students/1/edit
  def edit
    @student = Student.find(params[:id])
  end

  # POST /students
  # POST /students.json
  def create
    @student = Student.new(params[:student])

    respond_to do |format|
      if @student.save
        format.html { redirect_to @student, notice: 'Student was successfully created.' }
        format.json { render json: @student, status: :created, location: @student }
      else
        format.html { render action: "new" }
        format.json { render json: @student.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /students/1
  # PUT /students/1.json
  def update
    @student = Student.find(params[:id])
    @projects = @student.projects
    respond_to do |format|
      if @student.update_attributes(params[:student])
        if @student.inactif == TRUE
          @student.projects.delete(@projects)
        end
        format.html { redirect_to @student, notice: 'Student was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @student.errors, status: :unprocessable_entity }
      end
    end
  end
  # DELETE /students/1
  # DELETE /students/1.json
  def destroy
    @student = Student.find(params[:id])
    @student.destroy

    respond_to do |format|
      format.html { redirect_to students_url }
      format.json { head :no_content }
    end
  end
end
